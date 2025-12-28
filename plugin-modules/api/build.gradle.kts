plugins {
    id("org.bsdevelopment.java-conventions")
}

group = "org.bsdevelopment.simplepets"
description = "api"

dependencies {
    compileOnly(libs.spigotapi)

    implementation(libs.bslib)
}