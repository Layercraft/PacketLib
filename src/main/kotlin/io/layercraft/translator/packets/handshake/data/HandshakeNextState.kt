package io.layercraft.translator.packets.handshake.data

import io.layercraft.translator.packets.PacketState

enum class HandshakeNextState {
    STATUS,
    LOGIN,
    ;

    companion object {
        fun fromPacketState(packetState: PacketState): HandshakeNextState {
            return when (packetState) {
                PacketState.STATUS -> STATUS
                PacketState.LOGIN -> LOGIN
                else -> throw IllegalArgumentException("Invalid packet state: $packetState")
            }
        }
    }

    fun toPacketState(): PacketState {
        return when (this) {
            STATUS -> PacketState.STATUS
            LOGIN -> PacketState.LOGIN
        }
    }
}