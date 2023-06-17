plugins {
    kotlin("jvm") version "1.8.20"
    kotlin("plugin.serialization") version "1.8.21"
}

group = "io.layercraft"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    implementation("com.squareup:kotlinpoet:1.14.2")

    implementation("io.ktor:ktor-client-core:2.3.1")
    implementation("io.ktor:ktor-client-java:2.3.1")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

tasks.register<JavaExec>("generateCode") {
    dependsOn("compileKotlin")
    mainClass.set("GenerateCodeKt")
    classpath = sourceSets["main"].runtimeClasspath
}