plugins {
    id("org.bsdevelopment.java-conventions")
}

group = "org.bsdevelopment.simplepets"
description = "api"

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.21.6-R0.1-SNAPSHOT")
    implementation(libs.pluginutils)
    compileOnly(libs.brigadier)
}