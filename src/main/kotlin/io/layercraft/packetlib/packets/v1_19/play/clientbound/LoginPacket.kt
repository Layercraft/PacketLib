package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 *  | 0x23 | play | clientbound
 *
 * @property entityId entityId
 * @property isHardcore isHardcore
 * @property gameMode gameMode
 * @property previousGameMode previousGameMode
 * @property dimensionCodec dimensionCodec
 * @property worldType worldType
 * @property worldName worldName
 * @property hashedSeed hashedSeed
 * @property maxPlayers maxPlayers
 * @property viewDistance viewDistance
 * @property simulationDistance simulationDistance
 * @property reducedDebugInfo reducedDebugInfo
 * @property enableRespawnScreen enableRespawnScreen
 * @property isDebug isDebug
 * @property isFlat isFlat
 * @see <a href="https://wiki.vg/Protocol#Login_.28play.29">https://wiki.vg/Protocol#Login_.28play.29</a>
 */

@MinecraftPacket(id = 0x23, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class LoginPacket(
    val entityId: Int,
    val isHardcore: Boolean,
    val gameMode: UByte,
    val previousGameMode: Byte,
    val dimensionCodec: ByteArray,
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
) : ClientBoundPacket {

    companion object : PacketSerializer<LoginPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): LoginPacket {
            val entityId = input.readInt()
            val isHardcore = input.readBoolean()
            val gameMode = input.readUByte()
            val previousGameMode = input.readByte()
            val dimensionCodec = input.readNBT()
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

            return LoginPacket(entityId, isHardcore, gameMode, previousGameMode, dimensionCodec, worldType, worldName, hashedSeed, maxPlayers, viewDistance, simulationDistance, reducedDebugInfo, enableRespawnScreen, isDebug, isFlat)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: LoginPacket) {
            output.writeInt(value.entityId)
            output.writeBoolean(value.isHardcore)
            output.writeUByte(value.gameMode)
            output.writeByte(value.previousGameMode)
            output.writeBytes(value.dimensionCodec)
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
        }
    }
}