[![](https://jitpack.io/v/Layercraft/PacketLib.svg)](https://jitpack.io/#Layercraft/PacketLib)
[![Java CI with Gradle](https://github.com/Layercraft/PacketLib/actions/workflows/gradle-build.yml/badge.svg)](https://github.com/Layercraft/PacketLib/actions/workflows/gradle-build.yml)
[![Gradle Publish](https://github.com/Layercraft/PacketLib/actions/workflows/gradle-publish.yml/badge.svg)](https://github.com/Layercraft/PacketLib/actions/workflows/gradle-publish.yml)

# PacketLib - Type-safe Minecraft protocol packets for Kotlin

Type-safe, auto-generated Minecraft protocol packets in Kotlin — for **every release version from 1.7 to 1.21.11**
(55 versions, 43 distinct protocol definitions, ~7200 packet classes).

The packets are generated with [KotlinPoet](https://square.github.io/kotlinpoet/) from
[PrismarineJS/minecraft-data](https://github.com/PrismarineJS/minecraft-data).
Most packets are fully typed data classes with generated serializers; packets that use protocol
constructs that cannot be expressed in a typed way (entity metadata, particles, registry holders, ...)
fall back to a `raw: ByteArray` payload so every packet of every version still round-trips correctly.

## Usage

Every version has a codec in `MinecraftCodecs`:

```kotlin
val codec = MinecraftCodecs.V1_20_1
// or dynamically:
val codec = MinecraftCodecs.fromProtocolVersion(ProtocolVersion.fromProtocolNumber(763)!!)

val codecPacket = codec.getCodecPacket(PacketDirection.SERVERBOUND, PacketState.HANDSHAKING, 0x00)
```

Packets are plain data classes with a companion `PacketSerializer`:

```kotlin
import io.layercraft.packetlib.packets.v1_20.handshaking.serverbound.SetProtocolPacket

val packet = SetProtocolPacket(763, "localhost", 25565u, 1)
SetProtocolPacket.serialize(output, packet)
val decoded = SetProtocolPacket.deserialize(input)
```

`input`/`output` are your implementations of `MCProtocolDeserializer` / `MCProtocolSerializer`
(see the example implementations: https://gist.github.com/Newspicel/54184b844dee00a4f351e58de033e071).

## Regenerating the packets

The `:generator` module downloads the protocol definitions from minecraft-data (cached in
`generator/.cache`) and regenerates all packet classes, codecs and the `ProtocolVersion` enum:

```bash
./gradlew :generator:run                      # all release versions
./gradlew :generator:run -PmcVersions=1.20.1  # only specific versions
```

## Repo

### Jitpack:

<details open>
<summary>Gradle Kotlin</summary>

```kotlin
repositories {
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.layercraft:packetlib:<version>")
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
    implementation 'com.github.layercraft:packetlib:<version>'
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
            <version>&lt;version&gt;</version>
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
        url = uri("https://maven.pkg.github.com/Layercraft/PacketLib")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
        }
    }
}

dependencies {
    implementation("io.layercraft.connector:packetlib:<version>")
}
```

</details>

## Thanks

- [PrismarineJS/minecraft-data](https://github.com/PrismarineJS/minecraft-data) for the protocol data
- [minecraft.wiki](https://minecraft.wiki/w/Java_Edition_protocol) (formerly wiki.vg) for the protocol documentation
