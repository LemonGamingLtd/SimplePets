pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

rootProject.name = "SimplePets"
include(":api")
include(":main")

include(":versions:v1_21_6")

project(":api").projectDir  = file("plugin-modules/api")
project(":main").projectDir = file("plugin-modules/main")
