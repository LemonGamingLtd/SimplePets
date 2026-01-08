plugins {
    id("org.bsdevelopment.java-conventions")
    alias(libs.plugins.paperweight)
    alias(libs.plugins.shadow)
}

var mcVersion = "1.21.10"
var spigotNMS = "v1_21_R6"

var nmsVersion = "v$mcVersion".replace(".", "_")
var latestMinecraft = "$mcVersion-R0.1-SNAPSHOT"

dependencies {
    compileOnly(project(":api"))
    compileOnly(project(":main"))
    implementation(project(":versions"))

    compileOnly(libs.bslib)
    paperweight.paperDevBundle(latestMinecraft)
}

// TODO: This needs to be removed once 26.1 comes out
paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.REOBF_PRODUCTION

tasks.assemble {
    dependsOn(tasks.reobfJar)
}

tasks {
    shadowJar {
        archiveBaseName.set("SimplePets")
        archiveVersion.set(mcVersion)
        archiveClassifier.set("")

        var groupID = "simplepets.brainsynder"
        relocate("lib.brainsynder", "$groupID.libs.bslib")
        relocate("$groupID.nms", "$groupID.versions.$nmsVersion")

        // TODO: This needs to be removed once 26.1 comes out
        relocate("org.bukkit.craftbukkit", "org.bukkit.craftbukkit.$spigotNMS")
    }
}

