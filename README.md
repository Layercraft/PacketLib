[![](https://jitpack.io/v/Layercraft/PacketLib.svg)](https://jitpack.io/#Layercraft/PacketLib)
[![Java CI with Gradle](https://github.com/Layercraft/PacketLib/actions/workflows/gradle-build.yml/badge.svg)](https://github.com/Layercraft/PacketLib/actions/workflows/gradle-build.yml)
[![Gradle Publish](https://github.com/Layercraft/PacketLib/actions/workflows/gradle-publish.yml/badge.svg)](https://github.com/Layercraft/PacketLib/actions/workflows/gradle-publish.yml)
[![Deploy Dokka KDocs with GitHub Pages](https://github.com/Layercraft/PacketLib/actions/workflows/kdocs.yml/badge.svg)](https://github.com/Layercraft/PacketLib/actions/workflows/kdocs.yml)

# PacketLib - A library for serializing Minecraft Packets typesafe in Kotlin

KDocs: [PacketLib KDocs](https://packetlib.kdocs.layercraft.io/)

Type Safe generated Minecraft Protocol Packets in Kotlin.
For current versions: 1.19.3(also 1.19.4).
But some complex Packets are not implemented yet.

### Prewords
Special Thanks to [wiki.vg](https://wiki.vg) for the protocol documentation.
And to [PrismarineJS/minecraft-data](https://github.com/PrismarineJS/minecraft-data) for the protocol data as json.

## Repo

### Jitpack:

<details open>
<summary>Gradle Kotlin</summary>

```kotlin
repositories {
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.layercraft:packetlib:0.0.23")
}
```

</details>

<details>
<summary>Gradle Groovy</summary>

```groovy
repositories {
    maven { url = 'https://jitpack.io' }
}

dependencies {
    compile 'com.github.layercraft:packetlib:0.0.23'
}
```

</details>

<details>
<summary>Maven</summary>

```xml

<project>
    ...
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
    ...
    <dependencies>
        <dependency>
            <groupId>com.github.layercraft</groupId>
            <artifactId>packetlib</artifactId>
            <version>0.0.23</version>
        </dependency>
    </dependencies>
    ...
</project>
```

</details>

### Github Packages:

<details>
<summary>Gradle Kotlin</summary>

```kotlin
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/Layercraft/Translator-API")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
        }
    }
}

dependencies {
    implementation("io.layercraft.connector:translator-api:0.0.23")
}
```

</details>


<details>
<summary>Gradle Groovy</summary>

```groovy
repositories {
    maven {
        url = 'https://maven.pkg.github.com/Layercraft/Translator-API'
        credentials {
            username = project.findProperty("gpr.user") ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") ?: System.getenv("TOKEN")
        }
    }
}

dependencies {
    compile 'io.layercraft.connector:translator-api:0.0.23'
}
```

</details>

<details>
<summary>Maven</summary>

```xml

<project>
    ...
    <repositories>
        <repository>
            <id>Translator-API</id>
            <url>https://maven.pkg.github.com/Layercraft/Translator-API</url>
        </repository>
    </repositories>
    ...
    <dependencies>
        <dependency>
            <groupId>io.layercraft.connector</groupId>
            <artifactId>translator-api</artifactId>
            <version>0.0.23</version>
        </dependency>
    </dependencies>
    ...
</project>
```

Settings.xml

```xml

<settings>
    ...
    <servers>
        <server>
            <id>Translator-API</id>
            <username>USERNAME</username>
            <password>TOKEN</password>
        </server>
    </servers>
    ...
</settings>
```

</details>


## Serialization Implementations

There are no more default implementations for serialization in this library, so you have to write your own.
But here are some old example implementations: https://gist.github.com/Newspicel/54184b844dee00a4f351e58de033e071

So you need to implement the `MCProtocolSerializer` interface and the `MCProtocolDeserializer` interface. Then just get your Packet over the `MinecraftCodecs` and serialize it.