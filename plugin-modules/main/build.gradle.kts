import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    id("org.bsdevelopment.java-conventions")
    alias(libs.plugins.shadow)
}

group = "org.bsdevelopment.simplepets"
description = "main"

dependencies {
    compileOnly(libs.spigotapi)
    compileOnly(project(":api"))

    compileOnly(libs.protocollib)
    compileOnly(libs.commonsio)

    implementation(libs.bslib) {
        exclude(group = "io.papermc", module = "paperlib")
        exclude(group = "de.tr7zw", module = "item-nbt-api-plugin")
    }
    implementation(libs.updatechecker)
    implementation(libs.bstats)
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }

    processResources {
        filesMatching("plugin.yml") {
            filteringCharset = "UTF-8"
            filter<ReplaceTokens>(
                "tokens" to mapOf(
                    "VERSION" to project.version.toString()
                )
            )
        }
    }

    shadowJar {
        archiveBaseName.set("SimplePets")
        archiveClassifier.set("")
        archiveVersion.set("")

        var groupID = "simplepets.brainsynder"

        relocate("com.jeff_media.updatechecker", "$groupID.libs.updatechecker")
        relocate("io.papermc.lib", "$groupID.libs.paperlib")
        relocate("lib.brainsynder", "$groupID.libs.bslib")
        relocate("org.bstats", "$groupID.libs.bstats")
    }
}