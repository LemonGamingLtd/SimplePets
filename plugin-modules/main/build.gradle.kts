import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    id("org.bsdevelopment.java-conventions")
    id("com.gradleup.shadow")
}

group = "org.bsdevelopment.simplepets"
description = "main"

dependencies {
    compileOnly(libs.spigotapi)
    compileOnly(project(":api"))

    compileOnly(libs.protocollib)
    compileOnly(libs.placeholderapi)
    compileOnly(libs.commonsio)

    implementation(libs.pluginutils) {
        exclude(group = "io.papermc", module = "paperlib")
        exclude(group = "de.tr7zw", module = "item-nbt-api-plugin")
    }
    implementation(libs.updatechecker)
    implementation(libs.bstats)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
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
    }
}