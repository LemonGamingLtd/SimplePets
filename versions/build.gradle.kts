plugins {
    id("org.bsdevelopment.java-conventions")
    alias(libs.plugins.paperweight)
}
var latestMinecraft = "1.21.11-R0.1-SNAPSHOT"

dependencies {
    compileOnly(project(":api"))
    compileOnly(project(":main"))
    compileOnly(libs.bslib)
    compileOnly(libs.pluginutils)
    paperweight.paperDevBundle(latestMinecraft)
}

