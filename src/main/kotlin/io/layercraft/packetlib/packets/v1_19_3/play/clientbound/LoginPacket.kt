package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.NBT
import io.layercraft.packetlib.types.Position
/**
 * Login (play) | 0x24 | play | clientbound
 *
 * @param entityId entityId
 * @param isHardcore isHardcore
 * @param gameMode gameMode
 * @param previousGameMode previousGameMode
 * @param worldNames worldNames
 * @param dimensionCodec dimensionCodec
 * @param worldType worldType
 * @param worldName worldName
 * @param hashedSeed hashedSeed
 * @param maxPlayers maxPlayers
 * @param viewDistance viewDistance
 * @param simulationDistance simulationDistance
 * @param reducedDebugInfo reducedDebugInfo
 * @param enableRespawnScreen enableRespawnScreen
 * @param isDebug isDebug
 * @param isFlat isFlat
 * @param hasDeath death is present
 * @param dimensionName dimensionName
 * @param location location
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Login_.28play.29">https://wiki.vg/Protocol#Login_.28play.29</a>
 */

@MinecraftPacket(id = 0x24, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class LoginPacket(
    val entityId: Int,
    val isHardcore: Boolean,
    val gameMode: UByte,
    val previousGameMode: Byte,
    val worldNames: List<String>, // varint array
    val dimensionCodec: NBT,
    val worldType: String,
    val worldName: String,
    val hashedSeed: Long,
    val maxPlayers: Int, // varint
    val viewDistance: Int, // varint
    val simulationDistance: Int, // varint
    val reducedDebugInfo: Boolean,
    val enableRespawnScreen: Boolean,
    val isDebug: Boolean,
    val isFlat: Boolean,
    val hasDeath: Boolean,
    val dimensionName: String?,
    val location: Position?,
) : ClientBoundPacket {
    companion object : PacketSerializer<LoginPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): LoginPacket {
            val entityId = input.readInt()
            val isHardcore = input.readBoolean()
            val gameMode = input.readUByte()
            val previousGameMode = input.readByte()
            val worldNames = input.readVarIntArray { arrayInput -> arrayInput.readString() }
            val dimensionCodec = input.readNbt()
            val worldType = input.readString()
            val worldName = input.readString()
            val hashedSeed = input.readLong()
            val maxPlayers = input.readVarInt()
            val viewDistance = input.readVarInt()
            val simulationDistance = input.readVarInt()
            val reducedDebugInfo = input.readBoolean()
            val enableRespawnScreen = input.readBoolean()
            val isDebug = input.readBoolean()
            val isFlat = input.readBoolean()
            val hasDeath = input.readBoolean()
            val dimensionName = if (hasDeath) input.readString() else null
            val location = if (hasDeath) input.readPosition() else null

            return LoginPacket(entityId, isHardcore, gameMode, previousGameMode, worldNames, dimensionCodec, worldType, worldName, hashedSeed, maxPlayers, viewDistance, simulationDistance, reducedDebugInfo, enableRespawnScreen, isDebug, isFlat, hasDeath, dimensionName, location)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: LoginPacket) {
            output.writeInt(value.entityId)
            output.writeBoolean(value.isHardcore)
            output.writeUByte(value.gameMode)
            output.writeByte(value.previousGameMode)
            output.writeVarIntArray(value.worldNames) { arrayValue, arrayOutput -> arrayOutput.writeString(arrayValue) }
            output.writeNbt(value.dimensionCodec)
            output.writeString(value.worldType)
            output.writeString(value.worldName)
            output.writeLong(value.hashedSeed)
            output.writeVarInt(value.maxPlayers)
            output.writeVarInt(value.viewDistance)
            output.writeVarInt(value.simulationDistance)
            output.writeBoolean(value.reducedDebugInfo)
            output.writeBoolean(value.enableRespawnScreen)
            output.writeBoolean(value.isDebug)
            output.writeBoolean(value.isFlat)
            output.writeBoolean(value.hasDeath)
            if (value.hasDeath) output.writeString(value.dimensionName!!)
            if (value.hasDeath) output.writePosition(value.location!!)
        }
    }
}