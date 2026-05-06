plugins {
    id("java")
    id("maven-publish")
    id("com.gradleup.shadow") apply false
}

group = "org.bsdevelopment.simplepets"
version = "0.1.0"

allprojects {
    apply(plugin = "java")
    version = (findProperty("version") ?: "0.0.0-SNAPSHOT").toString()

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    }

    repositories {
        mavenLocal()
    }
}

fun Project.outputJarProducer(): Pair<TaskProvider<*>, Provider<RegularFile>> {
    return when {
        tasks.names.contains("reobfJar") -> {
            val task = tasks.named("reobfJar")
            val output: Provider<RegularFile> = task.flatMap { task ->
                (task.javaClass.getMethod("getOutputJar").invoke(task) as Provider<RegularFile>)
            }

            task to output
        }

        tasks.names.contains("shadowJar") -> {
            val task = tasks.named("shadowJar", Jar::class.java)
            task to task.flatMap { it.archiveFile }
        }

        else -> {
            val task = tasks.named("jar", Jar::class.java)
            task to task.flatMap { it.archiveFile }
        }
    }
}


subprojects.forEach { subproject ->
    evaluationDependsOn(subproject.path)
}

tasks.register<Jar>("multiprojectJar") {
    group = "build"
    description = "Merges all subproject jars into a single jar"
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    archiveBaseName.set("SimplePets")
    archiveVersion.set("")
    archiveClassifier.set("")

    val projectsToMerge = subprojects.filter { it.path != ":versions" }

    val producers = projectsToMerge.map { it.outputJarProducer() }

    dependsOn(producers.map { it.first })

    producers.forEach { (_, fileProvider) ->
        from(fileProvider.map { zipTree(it.asFile) }) {
            exclude(
                "plugin.yml",
                "META-INF/*.SF",
                "META-INF/*.DSA",
                "META-INF/*.RSA"
            )
        }
    }

    val mainProcessResources = project(":main").tasks.named("processResources")
    dependsOn(mainProcessResources)

    from(project(":main").layout.buildDirectory.dir("resources/main")) {
        include("plugin.yml")
        into("")
    }
}

tasks.named("build") {
    dependsOn(tasks.named("multiprojectJar"))
    mustRunAfter("clean")
}

tasks.register("release") {
    group = "build"
    description = "Cleans, builds the full jar, and publishes the API module"
    dependsOn("clean", "build", ":api:publish")
    project(":api").tasks.named("publish") {
        mustRunAfter(tasks.named("build"))
    }
}

artifacts {
    add("archives", tasks.named("multiprojectJar"))
}

fileTree("gradle") { include("*.gradle.kts") }.forEach { apply(from = it) }
