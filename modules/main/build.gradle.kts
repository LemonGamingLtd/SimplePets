plugins {
    id("org.bsdevelopment.java-conventions")
}

group = "org.bsdevelopment.simplepets"
description = "main"

val versionModules = project(":versions").subprojects


dependencies {
    compileOnly("org.spigotmc:spigot-api:1.21.4-R0.1-SNAPSHOT")

    implementation(libs.pluginutils)
    implementation(libs.commandapi)
    implementation(project(":api"))

    // Fetch and add all the versions
    versionModules.forEach { versionProj ->
        implementation(project(mapOf(
            "path" to versionProj.path,
            "configuration" to "reobf"
        )))
    }
}



tasks {
    processResources {
        filesMatching(listOf("plugin.yml")) {
            // filter<ReplaceTokens>("tokens" to mapOf("VERSION" to version, "BUILD_NUMBER" to (System.getenv("BUILD_NUMBER") ?: "")))
        }
    }

    shadowJar {
        archiveBaseName.set("SimplePets")
        archiveClassifier.set("")
        archiveVersion.set("")

        var groupID = "org.bsdevelopment.simplepets"

        relocate("dev.jorel.commandapi", "$groupID.libs.commandapi")
        relocate("com.eclipsesource.json", "$groupID.libs.json")
        relocate("com.github.Anon8281.universalScheduler", "$groupID.libs.scheduler")
        relocate("de.tr7zw.changeme.nbtapi", "$groupID.libs.nbtapi")
        relocate("io.papermc.lib", "$groupID.libs.paperlib")
        relocate("org.bsdevelopment.pluginutils", "$groupID.libs.pluginutils")
    }
}