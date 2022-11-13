package io.layercraft.translator.packets.play.clientbound

import io.layercraft.translator.packets.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.translator.types.Position

/**
 * Login (play) | 0x25 | play | client-bound
 *
 * @property entityId The player's Entity ID (EID).
 * @property isHardcore
 * @property gameMode 0: Survival, 1: Creative, 2: Adventure, 3: Spectator.
 * @property previousGameMode 0: survival, 1: creative, 2: adventure, 3: spectator. The hardcore flag is not included. The previous gamemode. Defaults to -1 if there is no previous gamemode.
 * @property dimensionNames Identifiers for all dimensions on the server.
 * @property registryCodec Represents certain registries that are sent from the server and are applied on the client.
 * @property dimensionType Name of the dimension type being spawned into.
 * @property dimensionName Name of the dimension being spawned into.
 * @property hashedSeed First 8 bytes of the SHA-256 hash of the world's seed. Used client side for biome noise
 * @property maxPlayers Was once used by the client to draw the player list, but now is ignored.
 * @property viewDistance Render distance (2-32).
 * @property simulationDistance The distance that the client will process specific things, such as entities.
 * @property reducedDebugInfo If true, a Notchian client shows reduced information on the debug screen. For servers in development, this should almost always be false.
 * @property enableRespawnScreen Set to false when the doImmediateRespawn gamerule is true.
 * @property isDebug True if the world is a debug mode world; debug mode worlds cannot be modified and have predefined blocks.
 * @property isFlat True if the world is a superflat world; flat worlds have different void fog and a horizon at y=0 instead of y=63.
 * @property hasDeathLocation If true, then the next two fields are present.
 * @property deathDimensionName Name of the dimension the player died in.
 * @property deathPosition The location that the player died at.
 * @see <a href="https://wiki.vg/Protocol#Login">https://wiki.vg/Protocol#Login</a>
 */
@MinecraftPacket(0x25, PacketState.PLAY, PacketDirection.CLIENTBOUND)
data class Login(
    val entityId: Int, //varint
    val isHardcore: Boolean,
    val gameMode: UByte,
    val previousGameMode: UByte,
    val dimensionNames: List<String>,
    val registryCodec: ByteArray, //nbt
    val dimensionType: String, //identifier
    val dimensionName: String, //identifier
    val hashedSeed: Long,
    val maxPlayers: Int, //varint, ignored
    val viewDistance: Int, //varint
    val simulationDistance: Int, //varint
    val reducedDebugInfo: Boolean,
    val enableRespawnScreen: Boolean,
    val isDebug: Boolean,
    val isFlat: Boolean,
    val hasDeathLocation: Boolean,
    val deathDimensionName: String?, //optional, identifier
    val deathPosition: Position?, //optional
): ClientBoundPacket {
    companion object: PacketSerializer<Login>{
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): Login {
            val entityId = input.readVarInt()
            val isHardcore = input.readBoolean()
            val gameMode = input.readUByte()
            val previousGameMode = input.readUByte()
            val dimensionNames = input.readVarIntArray { input.readIdentifier() }
            val registryCodec = input.readVarIntByteArray()
            val dimensionType = input.readIdentifier()
            val dimensionName = input.readIdentifier()
            val hashedSeed = input.readLong()
            val maxPlayers = input.readVarInt()
            val viewDistance = input.readVarInt()
            val simulationDistance = input.readVarInt()
            val reducedDebugInfo = input.readBoolean()
            val enableRespawnScreen = input.readBoolean()
            val isDebug = input.readBoolean()
            val isFlat = input.readBoolean()
            val hasDeathLocation = input.readBoolean()
            val deathDimensionName = if (hasDeathLocation) input.readIdentifier() else null
            val deathPosition = if (hasDeathLocation) input.readPosition() else null

            return Login(
                entityId = entityId,
                isHardcore = isHardcore,
                gameMode = gameMode,
                previousGameMode = previousGameMode,
                dimensionNames = dimensionNames,
                registryCodec = registryCodec,
                dimensionType = dimensionType,
                dimensionName = dimensionName,
                hashedSeed = hashedSeed,
                maxPlayers = maxPlayers,
                viewDistance = viewDistance,
                simulationDistance = simulationDistance,
                reducedDebugInfo = reducedDebugInfo,
                enableRespawnScreen = enableRespawnScreen,
                isDebug = isDebug,
                isFlat = isFlat,
                hasDeathLocation = hasDeathLocation,
                deathDimensionName = deathDimensionName,
                deathPosition = deathPosition
            )

        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: Login) {
            output.writeVarInt(value.entityId)
            output.writeBoolean(value.isHardcore)
            output.writeUByte(value.gameMode)
            output.writeUByte(value.previousGameMode)
            output.writeVarIntArray(value.dimensionNames) { str, outputArray ->  outputArray.writeIdentifier(str) }
            output.writeVarIntByteArray(value.registryCodec)
            output.writeIdentifier(value.dimensionType)
            output.writeIdentifier(value.dimensionName)
            output.writeLong(value.hashedSeed)
            output.writeVarInt(value.maxPlayers)
            output.writeVarInt(value.viewDistance)
            output.writeVarInt(value.simulationDistance)
            output.writeBoolean(value.reducedDebugInfo)
            output.writeBoolean(value.enableRespawnScreen)
            output.writeBoolean(value.isDebug)
            output.writeBoolean(value.isFlat)
            output.writeBoolean(value.hasDeathLocation)
            if (value.hasDeathLocation) {
                output.writeIdentifier(value.deathDimensionName!!)
                output.writePosition(value.deathPosition!!)
            }
        }
    }
}