plugins {
    id("org.bsdevelopment.java-conventions")
    alias(libs.plugins.paperweight)
    alias(libs.plugins.shadow)
}

var mcVersion = "26.1"

var nmsVersion = "v$mcVersion".replace(".", "_")
var latestMinecraft = "$mcVersion-R0.1-SNAPSHOT"

dependencies {
    compileOnly(project(":api"))
    compileOnly(project(":main"))
    implementation(project(":versions"))

    compileOnly(libs.bslib)
    compileOnly(libs.pluginutils)
    paperweight.paperDevBundle(latestMinecraft)
}

tasks {
    shadowJar {
        archiveBaseName.set("SimplePets")
        archiveVersion.set(mcVersion)
        archiveClassifier.set("")

        var groupID = "simplepets.brainsynder"
        relocate("lib.brainsynder", "$groupID.libs.bslib")
        relocate("$groupID.nms", "$groupID.versions.$nmsVersion")
    }
}
