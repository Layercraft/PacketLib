package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Click Container | 0x0a | play | serverbound
 *
 * @param windowId windowId
 * @param stateId stateId
 * @param slot slot
 * @param mouseButton mouseButton
 * @param mode mode
 * @param changedSlots list of WindowClickPacketChangedSlots
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Click_Container">https://wiki.vg/Protocol#Click_Container</a>
 */

data class WindowClickPacket(
    val windowId: UByte,
    val stateId: Int, // varint
    val slot: Short,
    val mouseButton: Byte,
    val mode: Int, // varint
    val changedSlots: List<WindowClickPacketChangedSlots>, // varint array
) : ServerBoundPacket {
    companion object : PacketSerializer<WindowClickPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): WindowClickPacket {
            val windowId = input.readUByte()
            val stateId = input.readVarInt()
            val slot = input.readShort()
            val mouseButton = input.readByte()
            val mode = input.readVarInt()
            val changedSlots = input.readVarIntArray { arrayInput ->
                val location = arrayInput.readShort()

                WindowClickPacketChangedSlots(location)
            }

            return WindowClickPacket(windowId, stateId, slot, mouseButton, mode, changedSlots)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: WindowClickPacket) {
            output.writeUByte(value.windowId)
            output.writeVarInt(value.stateId)
            output.writeShort(value.slot)
            output.writeByte(value.mouseButton)
            output.writeVarInt(value.mode)

            output.writeVarIntArray(value.changedSlots) { arrayValue, arrayOutput ->
                arrayOutput.writeShort(arrayValue.location)
            }
        }
    }
}

/**
 * WindowClickPacketChangedSlots
 *
 * @param location location
*/
data class WindowClickPacketChangedSlots(
    val location: Short,
)