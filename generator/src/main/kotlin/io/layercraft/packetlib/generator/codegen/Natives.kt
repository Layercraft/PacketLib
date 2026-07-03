package io.layercraft.packetlib.generator.codegen

import com.squareup.kotlinpoet.BOOLEAN
import com.squareup.kotlinpoet.BYTE
import com.squareup.kotlinpoet.BYTE_ARRAY
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.DOUBLE
import com.squareup.kotlinpoet.FLOAT
import com.squareup.kotlinpoet.INT
import com.squareup.kotlinpoet.LONG
import com.squareup.kotlinpoet.SHORT
import com.squareup.kotlinpoet.STRING
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.U_BYTE
import com.squareup.kotlinpoet.U_INT
import com.squareup.kotlinpoet.U_LONG
import com.squareup.kotlinpoet.U_SHORT
import com.squareup.kotlinpoet.asTypeName
import io.layercraft.packetlib.generator.protodef.NativeType
import io.layercraft.packetlib.generator.protodef.UnsupportedProtoException

enum class CompareKind { INT, LONG, STRING, BOOL }

/** How a value can be used as a `when` subject: kind plus the conversion suffix to normalize it. */
data class CompareInfo(val kind: CompareKind, val conversion: String = "")

/**
 * A leaf type: its Kotlin type, how to read it as an expression and write it as a statement.
 */
data class NativeSpec(
    val typeName: TypeName,
    val read: CodeBlock,
    val writeFormat: String, // %L is the access expression
    val compare: CompareInfo? = null,
    val nullable: Boolean = false,
)

/** The first protocol version using the x-z-y position encoding (1.14+). */
private const val MODERN_POSITION_PROTOCOL = 441

fun nativeSpec(type: NativeType, protocolNumber: Int): NativeSpec = when (type.name) {
    "varint", "optvarint" -> NativeSpec(INT, CodeBlock.of("input.readVarInt()"), "output.writeVarInt(%L)", CompareInfo(CompareKind.INT))
    "varlong" -> NativeSpec(LONG, CodeBlock.of("input.readVarLong()"), "output.writeVarLong(%L)", CompareInfo(CompareKind.LONG))
    "i8" -> NativeSpec(BYTE, CodeBlock.of("input.readByte()"), "output.writeByte(%L)", CompareInfo(CompareKind.INT, ".toInt()"))
    "u8" -> NativeSpec(U_BYTE, CodeBlock.of("input.readUByte()"), "output.writeUByte(%L)", CompareInfo(CompareKind.INT, ".toInt()"))
    "i16" -> NativeSpec(SHORT, CodeBlock.of("input.readShort()"), "output.writeShort(%L)", CompareInfo(CompareKind.INT, ".toInt()"))
    "u16" -> NativeSpec(U_SHORT, CodeBlock.of("input.readUShort()"), "output.writeUShort(%L)", CompareInfo(CompareKind.INT, ".toInt()"))
    "i32" -> NativeSpec(INT, CodeBlock.of("input.readInt()"), "output.writeInt(%L)", CompareInfo(CompareKind.INT))
    "u32" -> NativeSpec(U_INT, CodeBlock.of("input.readUInt()"), "output.writeUInt(%L)", CompareInfo(CompareKind.LONG, ".toLong()"))
    "i64" -> NativeSpec(LONG, CodeBlock.of("input.readLong()"), "output.writeLong(%L)", CompareInfo(CompareKind.LONG))
    "u64" -> NativeSpec(U_LONG, CodeBlock.of("input.readULong()"), "output.writeULong(%L)", CompareInfo(CompareKind.LONG, ".toLong()"))
    "f32" -> NativeSpec(FLOAT, CodeBlock.of("input.readFloat()"), "output.writeFloat(%L)")
    "f64" -> NativeSpec(DOUBLE, CodeBlock.of("input.readDouble()"), "output.writeDouble(%L)")
    "bool" -> NativeSpec(BOOLEAN, CodeBlock.of("input.readBoolean()"), "output.writeBoolean(%L)", CompareInfo(CompareKind.BOOL))
    "UUID" -> NativeSpec(java.util.UUID::class.asTypeName(), CodeBlock.of("input.readUUID()"), "output.writeUUID(%L)")
    "string" -> NativeSpec(STRING, CodeBlock.of("input.readString()"), "output.writeString(%L)", CompareInfo(CompareKind.STRING))
    "restBuffer" -> NativeSpec(BYTE_ARRAY, CodeBlock.of("input.readRemainingByteArray()"), "output.writeRemainingByteArray(%L)")
    "nbt" -> NativeSpec(Runtime.NBT, CodeBlock.of("input.readNbt()"), "output.writeNbt(%L)")
    "optionalNbt" -> NativeSpec(Runtime.NBT, CodeBlock.of("input.readOptionalNbt()"), "output.writeOptionalNbt(%L)", nullable = true)
    "anonymousNbt" -> NativeSpec(Runtime.NBT, CodeBlock.of("input.readAnonymousNbt()"), "output.writeAnonymousNbt(%L)")
    "anonOptionalNbt" -> NativeSpec(Runtime.NBT, CodeBlock.of("input.readAnonymousOptionalNbt()"), "output.writeAnonymousOptionalNbt(%L)", nullable = true)
    "position" ->
        if (protocolNumber >= MODERN_POSITION_PROTOCOL) {
            NativeSpec(Runtime.POSITION, CodeBlock.of("input.readPosition()"), "output.writePosition(%L)")
        } else {
            NativeSpec(Runtime.POSITION, CodeBlock.of("input.readLegacyPosition()"), "output.writeLegacyPosition(%L)")
        }
    "chunkBlockEntity" -> NativeSpec(Runtime.CHUNK_BLOCK_ENTITY, CodeBlock.of("input.readChunkBlockEntity()"), "output.writeChunkBlockEntity(%L)")
    else -> throw UnsupportedProtoException("native type '${type.name}'")
}

/** Read an integer count as an `Int` expression, and write a collection size back. */
data class CountCodec(val read: CodeBlock, val writeFormat: String)

fun countCodec(countTypeName: String): CountCodec = when (countTypeName) {
    "varint" -> CountCodec(CodeBlock.of("input.readVarInt()"), "output.writeVarInt(%L)")
    "varlong" -> CountCodec(CodeBlock.of("input.readVarLong().toInt()"), "output.writeVarLong(%L.toLong())")
    "i8" -> CountCodec(CodeBlock.of("input.readByte().toInt()"), "output.writeByte(%L.toByte())")
    "u8" -> CountCodec(CodeBlock.of("input.readUByte().toInt()"), "output.writeUByte(%L.toUByte())")
    "i16" -> CountCodec(CodeBlock.of("input.readShort().toInt()"), "output.writeShort(%L.toShort())")
    "u16" -> CountCodec(CodeBlock.of("input.readUShort().toInt()"), "output.writeUShort(%L.toUShort())")
    "i32" -> CountCodec(CodeBlock.of("input.readInt()"), "output.writeInt(%L)")
    "u32" -> CountCodec(CodeBlock.of("input.readUInt().toInt()"), "output.writeUInt(%L.toUInt())")
    "i64" -> CountCodec(CodeBlock.of("input.readLong().toInt()"), "output.writeLong(%L.toLong())")
    else -> throw UnsupportedProtoException("count type '$countTypeName'")
}

/** Read/write an integer type through a Long, for bitflags underlying storage. */
data class LongViaCodec(val read: CodeBlock, val writeFormat: String)

fun longViaCodec(name: String): LongViaCodec = when (name) {
    "varint" -> LongViaCodec(CodeBlock.of("input.readVarInt().toLong()"), "output.writeVarInt(%L.toInt())")
    "varlong" -> LongViaCodec(CodeBlock.of("input.readVarLong()"), "output.writeVarLong(%L)")
    "i8" -> LongViaCodec(CodeBlock.of("input.readByte().toLong()"), "output.writeByte(%L.toByte())")
    "u8" -> LongViaCodec(CodeBlock.of("input.readUByte().toLong()"), "output.writeUByte(%L.toUByte())")
    "i16" -> LongViaCodec(CodeBlock.of("input.readShort().toLong()"), "output.writeShort(%L.toShort())")
    "u16" -> LongViaCodec(CodeBlock.of("input.readUShort().toLong()"), "output.writeUShort(%L.toUShort())")
    "i32" -> LongViaCodec(CodeBlock.of("input.readInt().toLong()"), "output.writeInt(%L.toInt())")
    "u32" -> LongViaCodec(CodeBlock.of("input.readUInt().toLong()"), "output.writeUInt(%L.toUInt())")
    "i64" -> LongViaCodec(CodeBlock.of("input.readLong()"), "output.writeLong(%L)")
    "u64" -> LongViaCodec(CodeBlock.of("input.readULong().toLong()"), "output.writeULong(%L.toULong())")
    else -> throw UnsupportedProtoException("bitflags underlying type '$name'")
}
