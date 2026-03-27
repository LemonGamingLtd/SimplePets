import org.bsdevelopment.spigotweight.extension.UserdevExtension

plugins {
    id("org.bsdevelopment.java-conventions")
    id("org.bsdevelopment.spigotweight.userdev") version "1.0.8-SNAPSHOT"
}
var latestMinecraft = "26.1"

dependencies {
    compileOnly(project(":api"))
    compileOnly(project(":main"))
    compileOnly(libs.bslib)
    compileOnly(libs.pluginutils)
}

spigotweight {
    minecraftVersion = latestMinecraft
    target = UserdevExtension.SpigotAPITarget.SPIGOT   // SPIGOT or PAPER
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

