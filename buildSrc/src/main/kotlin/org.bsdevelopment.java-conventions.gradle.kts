import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

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
    maven("https://repo.dmulloy2.net/repository/public/")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://libraries.minecraft.net/")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

val groupID = "simplepets.brainsynder"
tasks.withType<ShadowJar>().configureEach {
    relocate("lib.brainsynder",                "$groupID.libs.bslib")
    relocate("com.jeff_media.updatechecker",   "$groupID.libs.updatechecker")
    relocate("io.papermc.lib",                 "$groupID.libs.paperlib")
    relocate("org.bstats",                     "$groupID.libs.bstats")
    relocate("org.bsdevelopment",              "$groupID.libs.bsdev")
}