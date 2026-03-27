import org.bsdevelopment.spigotweight.extension.UserdevExtension

plugins {
    id("org.bsdevelopment.java-conventions")
    id("org.bsdevelopment.spigotweight.userdev") version "1.0.8-SNAPSHOT"
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
}

spigotweight {
    minecraftVersion = mcVersion
    target = UserdevExtension.SpigotAPITarget.SPIGOT   // SPIGOT or PAPER
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
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
