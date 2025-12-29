plugins {
    id("java")
    id("maven-publish")
    alias(libs.plugins.shadow) apply false
}

group = "org.bsdevelopment.simplepets"
version = "0.1.0"

allprojects {
    apply(plugin = "java")

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))
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
        } else -> {
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

    val projectsToMerge = subprojects.filter { it.path != ":versions" }

    val producers = projectsToMerge.map { it.outputJarProducer() }

    dependsOn(producers.map { it.first })

    producers.forEach { (_, fileProvider) ->
        from(fileProvider.map { zipTree(it.asFile) }) {
            exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA", "LICENSE")
        }
    }
}

tasks.named("build") {
    dependsOn(tasks.named("multiprojectJar"))
}

artifacts {
    add("archives", tasks.named("multiprojectJar"))
}