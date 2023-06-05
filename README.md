[![](https://jitpack.io/v/Layercraft/PacketLib.svg)](https://jitpack.io/#Layercraft/PacketLib)
[![Java CI with Gradle](https://github.com/Layercraft/PacketLib/actions/workflows/gradle-build.yml/badge.svg)](https://github.com/Layercraft/PacketLib/actions/workflows/gradle-build.yml)
[![Gradle Publish](https://github.com/Layercraft/PacketLib/actions/workflows/gradle-publish.yml/badge.svg)](https://github.com/Layercraft/PacketLib/actions/workflows/gradle-publish.yml)
[![Deploy Dokka KDocs with GitHub Pages](https://github.com/Layercraft/PacketLib/actions/workflows/kdocs.yml/badge.svg)](https://github.com/Layercraft/PacketLib/actions/workflows/kdocs.yml)

# PacketLib - A library for serializing Minecraft Packets typesafe in Kotlin

KDocs: [PacketLib KDocs](https://packetlib.kdocs.layercraft.io/)

Type Safe generated Minecraft Protocol Packets in Kotlin.
For current versions: 1.19.2, 1.19.3 & 1.19.4.
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

### Included Implementations
- [ByteBuffer (Java)](https://docs.oracle.com/javase/8/docs/api/java/nio/ByteBuffer.html) -> MinecraftByteBufferSerialize / MinecraftByteBufferDeserialize
- [InputStream (Java)](https://docs.oracle.com/javase/8/docs/api/java/io/InputStream.html) -> MinecraftInputStreamSerialize / MinecraftInputStreamDeserialize

### Write your own
To write your own implementation, you need to implement the following interfaces:
- MinecraftProtocolSerializeInterface
- MinecraftProtocolDeserializeInterface

So you can use your implementation with the MinecraftCodec. e.g.: Netty, Okio, ...

### Example Implementation

```kotlin
object TranslatorAPI {

    fun <T : Packet> decodeFromByteArray(bytes: ByteArray, serializer: PacketSerializer<T>): T {
        val byteBuffer = ByteBuffer.wrap(bytes)
        val deserialize = MinecraftByteBufferDeserialize(byteBuffer)

        return serializer.deserialize(deserialize)
    }

    fun <T : Packet> encodeToByteArray(value: T, serializer: PacketSerializer<T>): ByteArray {
        val byteBuffer = ByteBuffer.allocate(MAX_PACKET_SIZE)
        val serialize = MinecraftByteBufferSerialize(byteBuffer)

        serializer.serialize(serialize, value)

        val size = byteBuffer.position()
        val byteArray = ByteArray(size)
        byteBuffer.get(0, byteArray, 0, size)

        return byteArray
    }

    fun decodeFromInputWithCodec(codec: MinecraftCodec, input: MinecraftProtocolDeserializeInterface<*>, packetDirection: PacketDirection, packetState: PacketState, packetId: Int): Packet? {
        return codec.getCodecPacket(packetDirection, packetState, packetId)?.packetSerializer?.deserialize(input)
    }

    fun encodeToOutputWithCodec(codec: MinecraftCodec, output: MinecraftProtocolSerializeInterface<*>, value: Packet) {
        codec.getCodecPacketFromPacket(value)?.packetSerializer?.serialize(output, value)
    }
}
```

Serialize a packet to a byte array:

```kotlin
val packet = SetProtocolPacket(ProtocolVersion.V1_19_2.protocolNumber, "localhost", 25565, 1)

val bytes = TranslatorAPI.encodeToByteArray(packet, SetProtocolPacket)
```

Deserialize a packet from a byte array:

```kotlin
val packet = TranslatorAPI.decodeFromByteArray(bytes, SetProtocolPacket)
```
