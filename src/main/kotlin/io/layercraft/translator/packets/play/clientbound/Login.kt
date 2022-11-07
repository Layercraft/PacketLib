package io.layercraft.translator.packets.play.clientbound

import io.ktor.utils.io.core.Input
import io.ktor.utils.io.core.Output
import io.ktor.utils.io.core.readDouble
import io.ktor.utils.io.core.readLong
import io.ktor.utils.io.core.readUByte
import io.layercraft.translator.packets.ClientBoundPacket
import io.layercraft.translator.packets.MinecraftPacket
import io.layercraft.translator.packets.PacketDirection
import io.layercraft.translator.packets.PacketSerializer
import io.layercraft.translator.packets.PacketState
import io.layercraft.translator.types.Position
import io.layercraft.translator.utils.mc


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
        override fun serialize(input: Input): Login {
            val entityId = input.mc.readVarInt()
            val isHardcore = input.mc.readBoolean()
            val gameMode = input.mc.readUByte()
            val previousGameMode = input.mc.readUByte()
            val dimensionNames = input.mc.readVarIntArray { input.mc.readIdentifier() }
            val registryCodec = input.mc.readVarIntByteArray()
            val dimensionType = input.mc.readIdentifier()
            val dimensionName = input.mc.readIdentifier()
            val hashedSeed = input.mc.readLong()
            val maxPlayers = input.mc.readVarInt()
            val viewDistance = input.mc.readVarInt()
            val simulationDistance = input.mc.readVarInt()
            val reducedDebugInfo = input.mc.readBoolean()
            val enableRespawnScreen = input.mc.readBoolean()
            val isDebug = input.mc.readBoolean()
            val isFlat = input.mc.readBoolean()
            val hasDeathLocation = input.mc.readBoolean()
            val deathDimensionName = if (hasDeathLocation) input.mc.readIdentifier() else null
            val deathPosition = if (hasDeathLocation) input.mc.readPosition() else null

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

        override fun deserialize(output: Output, value: Login) {
            output.mc.writeVarInt(value.entityId)
            output.mc.writeBoolean(value.isHardcore)
            output.mc.writeUByte(value.gameMode)
            output.mc.writeUByte(value.previousGameMode)
            output.mc.writeVarIntArray(value.dimensionNames) { str, outputArray ->  outputArray.mc.writeIdentifier(str) }
            output.mc.writeVarIntByteArray(value.registryCodec)
            output.mc.writeIdentifier(value.dimensionType)
            output.mc.writeIdentifier(value.dimensionName)
            output.mc.writeLong(value.hashedSeed)
            output.mc.writeVarInt(value.maxPlayers)
            output.mc.writeVarInt(value.viewDistance)
            output.mc.writeVarInt(value.simulationDistance)
            output.mc.writeBoolean(value.reducedDebugInfo)
            output.mc.writeBoolean(value.enableRespawnScreen)
            output.mc.writeBoolean(value.isDebug)
            output.mc.writeBoolean(value.isFlat)
            output.mc.writeBoolean(value.hasDeathLocation)
            if (value.hasDeathLocation) {
                output.mc.writeIdentifier(value.deathDimensionName!!)
                output.mc.writePosition(value.deathPosition!!)
            }
        }
    }
}