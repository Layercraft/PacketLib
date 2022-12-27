# WIP: PacketLib

## Not safe for use currently.

Special Thanks to [wiki.vg](https://wiki.vg) for the protocol documentation.

Docs: [PacketLib KDocs](https://packetlib.kdocs.layercraft.io/)

## Usage
Serialize a packet to a byte array:
```kotlin
val packet = Handshake(ProtocolVersion.V_1_19_2.protocolNumber, "localhost", 25565, HandshakeNextState.LOGIN)

val bytes = TranslatorAPI.encodeToByteArray(packet, Handshake)
```

Deserialize a packet from a byte array:
```kotlin
val packet = TranslatorAPI.decodeFromByteArray(bytes, Handshake)
```
