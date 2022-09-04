# Translator-API


Special Thanks to [kt-mc-packet](https://github.com/DevSrSouza/kt-mc-packet) for the original idea for working with kotlin serialize and the implementation.
And to [wiki.vg](https://wiki.vg) for the protocol documentation.

## Usage
Serialize a packet to a byte array:
```kotlin
val packet = Handshake(ProtocolVersion.V_1_19_2.protocolNumber, "localhost", 25565, HandshakeNextState.LOGIN)

val bytes = TranslatorAPI.encodeToByteArray(Handshake.serializer(), packet)
```

Deserialize a packet from a byte array:
```kotlin
val packet = TranslatorAPI.decodeFromByteArray(Handshake.serializer(), bytes)
```
