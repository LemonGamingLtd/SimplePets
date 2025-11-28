pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

rootProject.name = "SimplePets"
include(":api")
include(":main")

include(":versions:v1_21_4")

project(":api").projectDir  = file("modules/api")
project(":main").projectDir = file("modules/main")
