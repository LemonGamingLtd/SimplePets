plugins {
    id("org.bsdevelopment.java-conventions")
    alias(libs.plugins.paperweight)
}
var latestMinecraft = "1.21.6-R0.1-SNAPSHOT"

dependencies {
    compileOnly(project(":api"))
    compileOnly(project(":main"))
    compileOnly(libs.bslib)
    paperweight.paperDevBundle(latestMinecraft)
}

