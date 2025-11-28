plugins {
    id("org.bsdevelopment.java-conventions")
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.17"
}
var latestMinecraft = "1.21.4-R0.1-SNAPSHOT"

subprojects {
    apply(plugin = "io.papermc.paperweight.userdev")

    paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.REOBF_PRODUCTION

    tasks.assemble {
        dependsOn(tasks.reobfJar)
    }
}

paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.REOBF_PRODUCTION

dependencies {
    compileOnly(project(":api"))
    implementation(libs.pluginutils)
    paperweight.paperDevBundle("$latestMinecraft")
}

