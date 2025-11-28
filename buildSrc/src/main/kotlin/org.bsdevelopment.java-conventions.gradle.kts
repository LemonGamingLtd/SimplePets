plugins {
    id("java")
    id("maven-publish")
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.codemc.io/repository/maven-public/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io/")
    maven("https://repo.bsdevelopment.org/releases/")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://libraries.minecraft.net/")
}

group = "org.bsdevelopment.simplepets"
version = "0.1.0"

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}