import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("org.bsdevelopment.java-conventions")
    id("maven-publish")
    alias(libs.plugins.shadow)
}

group = "org.bsdevelopment.simplepets"
description = "api"

dependencies {
    compileOnly(libs.spigotapi)
    compileOnly(libs.bslib)
    compileOnly(libs.pluginutils)

    compileOnly("org.jetbrains:annotations:26.0.2")
}

java {
    withSourcesJar()
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }

    shadowJar {
        archiveBaseName.set("SimplePets-API")
        archiveClassifier.set("")
        archiveVersion.set("")

        val groupID = "simplepets.brainsynder"
        relocate("lib.brainsynder", "$groupID.libs.bslib")
    }

    publish {
        dependsOn(shadowJar)
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = project.group.toString()
            artifactId = "api"
            version = project.version.toString()

            // Publish the relocated shaded jar as the main artifact
            artifact(tasks.named<ShadowJar>("shadowJar")) {
                classifier = null
            }

            // Optional: sources jar (unshaded)
            artifact(tasks.named("sourcesJar"))
        }
    }

    repositories {
        maven {
            name = "bs-repo"
            url = uri("https://repo.bsdevelopment.org/releases")
            credentials {
                username = findProperty("BS_REPO_USER") as String?
                password = findProperty("BS_REPO_PASS") as String?
            }
        }
    }
}