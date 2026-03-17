pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

rootProject.name = "SimplePets"
include(":api")
include(":main")

// include(":versions:v1_21_6") // Removed support in version R5-B296
include(":versions:v1_21_7")
include(":versions:v1_21_8")
include(":versions:v1_21_10")
include(":versions:v1_21_11")

project(":api").projectDir  = file("plugin-modules/api")
project(":main").projectDir = file("plugin-modules/main")
