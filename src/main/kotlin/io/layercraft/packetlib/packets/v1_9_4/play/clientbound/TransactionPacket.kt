package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 *  | 0x11 | play | clientbound
 *
 * @property windowId windowId
 * @property action action
 * @property accepted accepted
 * @see <a href="https://wiki.vg/Protocol#Confirm_Transaction_.28clientbound.29">https://wiki.vg/Protocol#Confirm_Transaction_.28clientbound.29</a>
 */

@MinecraftPacket(id = 0x11, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class TransactionPacket(
    val windowId: Byte,
    val action: Short,
    val accepted: Boolean,
) : ClientBoundPacket {

    companion object : PacketSerializer<TransactionPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): TransactionPacket {
            val windowId = input.readByte()
            val action = input.readShort()
            val accepted = input.readBoolean()

            return TransactionPacket(windowId, action, accepted)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: TransactionPacket) {
            output.writeByte(value.windowId)
            output.writeShort(value.action)
            output.writeBoolean(value.accepted)
        }
    }
}
