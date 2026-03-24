plugins {
    id("org.bsdevelopment.java-conventions")
    alias(libs.plugins.paperweight)
}
var latestMinecraft = "26.1"

dependencies {
    compileOnly(project(":api"))
    compileOnly(project(":main"))
    compileOnly(libs.bslib)
    compileOnly(libs.pluginutils)
    paperweight.paperDevBundle(latestMinecraft)
}

