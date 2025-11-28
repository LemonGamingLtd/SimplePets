plugins {
    id("java")
    id("maven-publish")
    alias(libs.plugins.shadow)
}

group = "org.bsdevelopment.simplepets"
version = "0.1.0"

allprojects {
    apply(plugin = "java")
    apply(plugin = "com.gradleup.shadow")

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    }


    tasks.assemble {
        dependsOn(tasks.shadowJar)
    }
}
