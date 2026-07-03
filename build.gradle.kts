plugins {
    alias(libs.plugins.kotlin.jvm)
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
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
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
