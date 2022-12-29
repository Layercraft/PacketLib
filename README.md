# WIP: PacketLib

## Not safe for use currently.

Special Thanks to [wiki.vg](https://wiki.vg) for the protocol documentation.

Docs: [PacketLib KDocs](https://packetlib.kdocs.layercraft.io/)

## Repo

### Github Packages:

#### Gradle Kotlin

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

#### Gradle Groovy

```groovy
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/Layercraft/Translator-API")
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

#### Maven

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

### Jitpack:

#### Gradle Kotlin:

```kotlin
repositories {
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.layercraft:packetlib:0.0.23")
}
```

#### Gradle Groovy:

```groovy
repositories {
    maven { url = 'https://jitpack.io' }
}

dependencies {
    compile 'com.github.layercraft:packetlib:0.0.23'
}
```

#### Maven:

```xml

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
<groupId>com.github.layercraft</groupId>
<artifactId>packetlib</artifactId>
<version>0.0.23</version>
</dependency>
```

## Usage

Serialize a packet to a byte array:

```kotlin
val packet = SetProtocolPacket(ProtocolVersion.V1_19_2.protocolNumber, "localhost", 25565, 1)

val bytes = TranslatorAPI.encodeToByteArray(packet, SetProtocolPacket)
```

Deserialize a packet from a byte array:

```kotlin
val packet = TranslatorAPI.decodeFromByteArray(bytes, SetProtocolPacket)
```
