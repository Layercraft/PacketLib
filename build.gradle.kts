plugins {
    kotlin("jvm") version "1.8.20"
    id("org.jetbrains.dokka") version "1.8.10"
    `maven-publish`
}

group = "io.layercraft.connector"
version = properties["version"] as String

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("reflect"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

publishing {
    repositories {
        maven {
            name = "repo"
            url = uri("https://maven.pkg.github.com/Layercraft/PacketLib")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}