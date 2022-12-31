package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Merchant Offers | 0x27 | play | clientbound
 *
 * @property windowId windowId
 * @property trades trades
 * @property villagerLevel villagerLevel
 * @property experience experience
 * @property isRegularVillager isRegularVillager
 * @property canRestock canRestock
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Merchant_Offers">https://wiki.vg/Protocol#Merchant_Offers</a>
 */

@MinecraftPacket(id = 0x27, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class TradeListPacket(
    val windowId: Int, // varint
    val trades: List<TradeListPacketTrades>, // varint array
    val villagerLevel: Int, // varint
    val experience: Int, // varint
    val isRegularVillager: Boolean,
    val canRestock: Boolean,
) : ClientBoundPacket {
    companion object : PacketSerializer<TradeListPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): TradeListPacket {
            val windowId = input.readVarInt()
            val trades = input.readVarIntArray { arrayInput ->
                val tradeDisabled = arrayInput.readBoolean()
                val nbTradeUses = arrayInput.readInt()
                val maximumNbTradeUses = arrayInput.readInt()
                val xp = arrayInput.readInt()
                val specialPrice = arrayInput.readInt()
                val priceMultiplier = arrayInput.readFloat()
                val demand = arrayInput.readInt()

                return@readVarIntArray TradeListPacketTrades(tradeDisabled, nbTradeUses, maximumNbTradeUses, xp, specialPrice, priceMultiplier, demand)
            }
            val villagerLevel = input.readVarInt()
            val experience = input.readVarInt()
            val isRegularVillager = input.readBoolean()
            val canRestock = input.readBoolean()

            return TradeListPacket(windowId, trades, villagerLevel, experience, isRegularVillager, canRestock)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: TradeListPacket) {
            output.writeVarInt(value.windowId)
            output.writeVarIntArray(value.trades) { arrayValue, arrayOutput ->
                arrayOutput.writeBoolean(arrayValue.tradeDisabled)
                arrayOutput.writeInt(arrayValue.nbTradeUses)
                arrayOutput.writeInt(arrayValue.maximumNbTradeUses)
                arrayOutput.writeInt(arrayValue.xp)
                arrayOutput.writeInt(arrayValue.specialPrice)
                arrayOutput.writeFloat(arrayValue.priceMultiplier)
                arrayOutput.writeInt(arrayValue.demand)
            }
            output.writeVarInt(value.villagerLevel)
            output.writeVarInt(value.experience)
            output.writeBoolean(value.isRegularVillager)
            output.writeBoolean(value.canRestock)
        }
    }
}

/**
 * TradeListPacketTrades | trades
 *
 * @property tradeDisabled tradeDisabled
 * @property nbTradeUses nbTradeUses
 * @property maximumNbTradeUses maximumNbTradeUses
 * @property xp xp
 * @property specialPrice specialPrice
 * @property priceMultiplier priceMultiplier
 * @property demand demand
*/
data class TradeListPacketTrades(
    val tradeDisabled: Boolean,
    val nbTradeUses: Int,
    val maximumNbTradeUses: Int,
    val xp: Int,
    val specialPrice: Int,
    val priceMultiplier: Float,
    val demand: Int,
)
