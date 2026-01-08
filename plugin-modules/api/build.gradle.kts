plugins {
    id("org.bsdevelopment.java-conventions")
    alias(libs.plugins.shadow)
}

group = "org.bsdevelopment.simplepets"
description = "api"

dependencies {
    compileOnly(libs.spigotapi)

    compileOnly(libs.bslib)
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }

    shadowJar {
        archiveBaseName.set("SimplePets-API")
        archiveClassifier.set("")
        archiveVersion.set("")

        var groupID = "simplepets.brainsynder"

        relocate("lib.brainsynder", "$groupID.libs.bslib")
    }
}