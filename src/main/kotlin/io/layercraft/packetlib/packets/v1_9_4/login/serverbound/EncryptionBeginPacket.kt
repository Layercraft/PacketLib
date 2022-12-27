package io.layercraft.packetlib.packets.v1_9_4.login.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Encryption Response | 0x01 | login | serverbound
 *

 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(packetId = 0x01, state = PacketState.LOGIN, direction = PacketDirection.SERVERBOUND)
 class EncryptionBeginPacket(

) : ServerBoundPacket {

    companion object : PacketSerializer<EncryptionBeginPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EncryptionBeginPacket {

            return EncryptionBeginPacket()
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EncryptionBeginPacket) {

        }
    }
}
