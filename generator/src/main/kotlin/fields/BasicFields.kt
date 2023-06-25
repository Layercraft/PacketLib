package fields

import io.layercraft.packetlib.types.NBT

object BasicFields {
    val VARINT =
        @FieldType("varint")
        object : BasicField(Int::class, "writeVarInt(%s)", "readVarInt()", "varint") {}

    val OPT_VARINT =
        @FieldType("optvarint")
        object : BasicField(Int::class, "writeVarInt(%s)", "readVarInt()", "optvarint", optional = true) {}

    val VARLONG =
        @FieldType("varlong")
        object : BasicField(Long::class, "writeVarLong(%s)", "readVarLong()", "varlong") {}

    val I8 =
        @FieldType("i8")
        object : BasicField(Byte::class, "writeByte(%s)", "readByte()") {}

    val U8 =
        @FieldType("u8")
        object : BasicField(UByte::class, "writeUByte(%s)", "readUByte()") {}

    val I16 =
        @FieldType("i16")
        object : BasicField(Short::class, "writeShort(%s)", "readShort()") {}

    val U16 =
        @FieldType("u16")
        object : BasicField(UShort::class, "writeUShort(%s)", "readUShort()") {}

    val I32 =
        @FieldType("i32")
        object : BasicField(Int::class, "writeInt(%s)", "readInt()") {}

    val U32 =
        @FieldType("u32")
        object : BasicField(UInt::class, "writeUInt(%s)", "readUInt()") {}

    val I64 =
        @FieldType("i64")
        object : BasicField(Long::class, "writeLong(%s)", "readLong()") {}

    val U64 =
        @FieldType("u64")
        object : BasicField(ULong::class, "writeULong(%s)", "readULong()") {}

    val F32 =
        @FieldType("f32")
        object : BasicField(Float::class, "writeFloat(%s)", "readFloat()") {}

    val F64 =
        @FieldType("f64")
        object : BasicField(Double::class, "writeDouble(%s)", "readDouble()") {}

    val BOOL =
        @FieldType("bool")
        object : BasicField(Boolean::class, "writeBoolean(%s)", "readBoolean()") {}

    val UUID =
        @FieldType("UUID")
        object : BasicField(String::class, "writeUUID(%s)", "readUUID()") {}

    val STRING =
        @FieldType("string")
        object : BasicField(String::class, "writeString(%s)", "readString()") {}

    val PSTRING =
        @FieldType("pstring")
        object : BasicField(String::class, "writeString(%s)", "readString()") {}

    val REST_BUFFER =
        @FieldType("restBuffer")
        object : BasicField(ByteArray::class, "writeRemainingByteArray(%s)", "readRemainingByteArray()", "restBuffer") {}

    val NBT =
        @FieldType("nbt")
        object : BasicField(NBT::class, "writeNBT(%s)", "readNBT()", "nbt") {}

    val ALL: List<Field> = listOf(
        VARINT,
        OPT_VARINT,
        VARLONG,
        I8,
        U8,
        I16,
        U16,
        I32,
        U32,
        I64,
        U64,
        F32,
        F64,
        BOOL,
        UUID,
        STRING,
        PSTRING,
        REST_BUFFER,
        NBT,
    )
}