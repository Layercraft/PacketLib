package io.layercraft.packetlib.types

import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import java.io.ByteArrayOutputStream

/**
 * Raw NBT bytes, exactly as they appear on the wire.
 */
typealias NBT = ByteArray

/**
 * Reads NBT structures from the protocol stream without interpreting them, returning the raw bytes.
 *
 * Supports the classic network format (named root tag, up to 1.20.1) and the anonymous network
 * format (root tag without a name, 1.20.2+).
 */
object NbtCodec {

    private const val TAG_END: Byte = 0

    /** Reads a full NBT structure with a named root tag. */
    fun readNamed(input: MCProtocolDeserializer<*>): NBT {
        val out = ByteArrayOutputStream()
        val tag = readByte(input, out)
        if (tag != TAG_END) {
            readName(input, out)
            readPayload(tag, input, out)
        }
        return out.toByteArray()
    }

    /** Reads a full NBT structure with a named root tag, or returns null if the first byte is TAG_End. */
    fun readOptionalNamed(input: MCProtocolDeserializer<*>): NBT? {
        val out = ByteArrayOutputStream()
        val tag = readByte(input, out)
        if (tag == TAG_END) return null
        readName(input, out)
        readPayload(tag, input, out)
        return out.toByteArray()
    }

    /** Reads a full NBT structure with an anonymous root tag (network format since 1.20.2). */
    fun readAnonymous(input: MCProtocolDeserializer<*>): NBT {
        val out = ByteArrayOutputStream()
        val tag = readByte(input, out)
        if (tag != TAG_END) {
            readPayload(tag, input, out)
        }
        return out.toByteArray()
    }

    /** Reads an anonymous NBT structure, or returns null if the first byte is TAG_End. */
    fun readOptionalAnonymous(input: MCProtocolDeserializer<*>): NBT? {
        val out = ByteArrayOutputStream()
        val tag = readByte(input, out)
        if (tag == TAG_END) return null
        readPayload(tag, input, out)
        return out.toByteArray()
    }

    private fun readByte(input: MCProtocolDeserializer<*>, out: ByteArrayOutputStream): Byte {
        val value = input.readByte()
        out.write(value.toInt())
        return value
    }

    private fun readInt(input: MCProtocolDeserializer<*>, out: ByteArrayOutputStream): Int {
        val value = input.readInt()
        out.write(value ushr 24)
        out.write(value ushr 16)
        out.write(value ushr 8)
        out.write(value)
        return value
    }

    private fun copy(input: MCProtocolDeserializer<*>, out: ByteArrayOutputStream, n: Int) {
        if (n > 0) out.writeBytes(input.readBytes(n))
    }

    private fun readName(input: MCProtocolDeserializer<*>, out: ByteArrayOutputStream) {
        val length = input.readUShort().toInt()
        out.write(length ushr 8)
        out.write(length)
        copy(input, out, length)
    }

    private fun readPayload(tag: Byte, input: MCProtocolDeserializer<*>, out: ByteArrayOutputStream) {
        when (tag.toInt()) {
            0 -> Unit // TAG_End
            1 -> copy(input, out, 1) // TAG_Byte
            2 -> copy(input, out, 2) // TAG_Short
            3 -> copy(input, out, 4) // TAG_Int
            4 -> copy(input, out, 8) // TAG_Long
            5 -> copy(input, out, 4) // TAG_Float
            6 -> copy(input, out, 8) // TAG_Double
            7 -> copy(input, out, readInt(input, out)) // TAG_Byte_Array
            8 -> readName(input, out) // TAG_String
            9 -> { // TAG_List
                val elementTag = readByte(input, out)
                val count = readInt(input, out)
                repeat(count) { readPayload(elementTag, input, out) }
            }
            10 -> { // TAG_Compound
                while (true) {
                    val childTag = readByte(input, out)
                    if (childTag == TAG_END) break
                    readName(input, out)
                    readPayload(childTag, input, out)
                }
            }
            11 -> copy(input, out, 4 * readInt(input, out)) // TAG_Int_Array
            12 -> copy(input, out, 8 * readInt(input, out)) // TAG_Long_Array
            else -> throw IllegalStateException("Unknown NBT tag: $tag")
        }
    }
}
