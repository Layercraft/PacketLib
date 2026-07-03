plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    application
}

group = "io.layercraft"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlinpoet)
    implementation(libs.kotlinx.serialization.json)
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("io.layercraft.packetlib.generator.MainKt")
}

tasks.named<JavaExec>("run") {
    // args: <output source dir> <cache dir> [versions...]
    args = listOf(
        rootProject.projectDir.resolve("src/main/kotlin").absolutePath,
        project.projectDir.resolve(".cache").absolutePath,
    ) + (project.findProperty("mcVersions")?.toString()?.split(",") ?: emptyList())
}
