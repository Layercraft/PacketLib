package io.layercraft.packetlib.generator.codegen

import com.squareup.kotlinpoet.BYTE_ARRAY
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.LIST
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.STRING
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import io.layercraft.packetlib.generator.UniqueNames
import io.layercraft.packetlib.generator.camelCase
import io.layercraft.packetlib.generator.pascalCase
import io.layercraft.packetlib.generator.protodef.ArrayType
import io.layercraft.packetlib.generator.protodef.BitfieldType
import io.layercraft.packetlib.generator.protodef.BitflagsType
import io.layercraft.packetlib.generator.protodef.BufferType
import io.layercraft.packetlib.generator.protodef.ContainerType
import io.layercraft.packetlib.generator.protodef.CountSpec
import io.layercraft.packetlib.generator.protodef.MapperType
import io.layercraft.packetlib.generator.protodef.NativeType
import io.layercraft.packetlib.generator.protodef.OptionType
import io.layercraft.packetlib.generator.protodef.PStringType
import io.layercraft.packetlib.generator.protodef.ProtoField
import io.layercraft.packetlib.generator.protodef.ProtoType
import io.layercraft.packetlib.generator.protodef.SwitchType
import io.layercraft.packetlib.generator.protodef.TypeReference
import io.layercraft.packetlib.generator.protodef.TypeResolver
import io.layercraft.packetlib.generator.protodef.UnsupportedProtoException
import io.layercraft.packetlib.generator.protodef.VoidType

/** A generated value: its Kotlin type, a read expression and write statements. */
data class ValueCode(
    val typeName: TypeName,
    val read: CodeBlock,
    val write: CodeBlock,
    val compare: CompareInfo? = null,
)

data class PropInfo(val name: String, val typeName: TypeName, val protoName: String)

/** Generated code for a container: constructor properties plus read/write statement blocks. */
data class ContainerCode(
    val properties: List<PropInfo>,
    val read: CodeBlock,
    val write: CodeBlock,
)

/**
 * Translates a ProtoDef packet tree into Kotlin code. Any construct that cannot be represented
 * throws [UnsupportedProtoException]; the caller falls back to a raw payload packet.
 */
class PacketBodyGen(
    private val resolver: TypeResolver,
    private val protocolNumber: Int,
    private val packageName: String,
) {

    /** Additional (top-level) classes generated for nested containers, bitfields and bitflags. */
    val extraTypes = mutableListOf<TypeSpec>()

    private val fileClassNames = UniqueNames()
    private var tempIndex = 0

    private fun temp(base: String): String = base + tempIndex++

    private class Scope(val parent: Scope?) {
        val entries = mutableMapOf<String, ScopeEntry>()
    }

    private data class ScopeEntry(
        val readRef: CodeBlock,
        val writeRef: CodeBlock,
        val compare: CompareInfo?,
        val nullable: Boolean,
    )

    fun claimClassName(name: String): String = fileClassNames.claim(name)

    fun genPacketBody(container: ContainerType, packetClassName: String): ContainerCode =
        genContainer(container.fields, Scope(null), CodeBlock.of("value"), packetClassName)

    // ---------------------------------------------------------------------------------------
    // Containers
    // ---------------------------------------------------------------------------------------

    private fun genContainer(
        fields: List<ProtoField>,
        parentScope: Scope?,
        writePrefix: CodeBlock,
        nameHint: String,
    ): ContainerCode {
        val scope = Scope(parentScope)
        val properties = mutableListOf<PropInfo>()
        val read = CodeBlock.builder()
        val write = CodeBlock.builder()
        val localNames = UniqueNames().apply { claim("input"); claim("output"); claim("value") }

        genContainerInto(fields, scope, writePrefix, nameHint, properties, read, write, localNames)

        return ContainerCode(properties, read.build(), write.build())
    }

    private fun genContainerInto(
        fields: List<ProtoField>,
        scope: Scope,
        writePrefix: CodeBlock,
        nameHint: String,
        properties: MutableList<PropInfo>,
        read: CodeBlock.Builder,
        write: CodeBlock.Builder,
        localNames: UniqueNames,
    ) {
        for (field in fields) {
            if (field.anon) {
                when (val resolved = resolver.deepResolve(field.type)) {
                    is ContainerType ->
                        genContainerInto(resolved.fields, scope, writePrefix, nameHint, properties, read, write, localNames)
                    else -> throw UnsupportedProtoException("anonymous ${resolved::class.simpleName} field")
                }
                continue
            }

            val protoName = field.name!!
            val propName = localNames.claim(camelCase(protoName))
            val writeAccess = CodeBlock.of("%L.%N", writePrefix, propName)

            val value = genValue(
                type = field.type,
                scope = scope,
                writeAccess = writeAccess,
                nameHint = nameHint + pascalCase(protoName),
            ) ?: continue // void — carries no data

            properties += PropInfo(propName, value.typeName, protoName)

            read.add("val %N = ", propName).add(value.read).add("\n")
            write.add(value.write)

            scope.entries[protoName] = ScopeEntry(
                readRef = CodeBlock.of("%N", propName),
                writeRef = writeAccess,
                compare = value.compare,
                nullable = value.typeName.isNullable,
            )
        }
    }

    // ---------------------------------------------------------------------------------------
    // Values
    // ---------------------------------------------------------------------------------------

    private fun genValue(
        type: ProtoType,
        scope: Scope,
        writeAccess: CodeBlock,
        nameHint: String,
    ): ValueCode? = when (type) {
        is VoidType -> null
        is NativeType -> {
            val spec = nativeSpec(type, protocolNumber)
            ValueCode(
                typeName = spec.typeName.copy(nullable = spec.nullable),
                read = spec.read,
                write = CodeBlock.builder().add(spec.writeFormat, writeAccess).add("\n").build(),
                compare = spec.compare,
            )
        }
        is TypeReference -> genValue(resolver.deepResolve(type), scope, writeAccess, nameHint)
        is ContainerType -> genContainerValue(type, scope, writeAccess, nameHint)
        is ArrayType -> genArray(type, scope, writeAccess, nameHint)
        is OptionType -> genOption(type, scope, writeAccess, nameHint)
        is BufferType -> genBuffer(type, scope, writeAccess)
        is PStringType -> genPString(type, writeAccess)
        is SwitchType -> genSwitch(type, scope, writeAccess, nameHint)
        is BitfieldType -> genBitfield(type, writeAccess, nameHint)
        is BitflagsType -> genBitflags(type, writeAccess, nameHint)
        is MapperType -> genMapper(type, writeAccess)
    }

    private fun genContainerValue(
        type: ContainerType,
        scope: Scope,
        writeAccess: CodeBlock,
        nameHint: String,
    ): ValueCode {
        val className = fileClassNames.claim(nameHint)
        val inner = genContainer(type.fields, scope, writeAccess, className)
        val classType = ClassName(packageName, className)
        extraTypes += buildDataClass(classType, inner.properties)

        val read = CodeBlock.builder()
        if (inner.properties.isEmpty()) {
            read.add("%T()", classType)
        } else {
            read.add("run {\n")
            read.indent()
            read.add(inner.read)
            read.add("%T(", classType)
            inner.properties.forEachIndexed { i, p -> read.add(if (i == 0) "%N" else ",·%N", p.name) }
            read.add(")\n")
            read.unindent()
            read.add("}")
        }

        return ValueCode(classType, read.build(), inner.write)
    }

    private fun buildDataClass(classType: ClassName, properties: List<PropInfo>): TypeSpec {
        val builder = TypeSpec.classBuilder(classType)
        if (properties.isNotEmpty()) {
            builder.addModifiers(KModifier.DATA)
            val constructor = FunSpec.constructorBuilder()
            for (p in properties) {
                constructor.addParameter(p.name, p.typeName)
                builder.addProperty(PropertySpec.builder(p.name, p.typeName).initializer("%N", p.name).build())
                builder.addKdoc("@param %N %L\n", p.name, p.protoName)
            }
            builder.primaryConstructor(constructor.build())
        }
        return builder.build()
    }

    // ---------------------------------------------------------------------------------------
    // Arrays, options, buffers, strings
    // ---------------------------------------------------------------------------------------

    private fun countRead(countType: ProtoType?, count: CountSpec?, scope: Scope): CodeBlock = when {
        countType != null -> {
            val name = (resolver.deepResolve(countType) as? NativeType)?.name
                ?: throw UnsupportedProtoException("non-native count type")
            countCodec(name).read
        }
        count is CountSpec.Fixed -> CodeBlock.of("%L", count.count)
        count is CountSpec.FieldReference -> {
            val entry = lookup(scope, count.path)
            fieldRefAsInt(entry, read = true)
        }
        else -> throw UnsupportedProtoException("array/buffer without count")
    }

    private fun countWrite(countType: ProtoType?, sizeAccess: CodeBlock): CodeBlock? {
        if (countType == null) return null // fixed or field-referenced counts are not re-written
        val name = (resolver.deepResolve(countType) as? NativeType)?.name
            ?: throw UnsupportedProtoException("non-native count type")
        return CodeBlock.builder().add(countCodec(name).writeFormat, sizeAccess).add("\n").build()
    }

    private fun fieldRefAsInt(entry: ScopeEntry, read: Boolean): CodeBlock {
        val ref = if (read) entry.readRef else entry.writeRef
        val compare = entry.compare ?: throw UnsupportedProtoException("count reference is not an integer field")
        val nullableMark = if (entry.nullable) "!!" else ""
        return when (compare.kind) {
            CompareKind.INT -> CodeBlock.of("%L%L%L", ref, nullableMark, compare.conversion)
            CompareKind.LONG -> CodeBlock.of("%L%L%L.toInt()", ref, nullableMark, compare.conversion.removeSuffix(".toLong()"))
            else -> throw UnsupportedProtoException("count reference is not an integer field")
        }
    }

    private fun genArray(type: ArrayType, scope: Scope, writeAccess: CodeBlock, nameHint: String): ValueCode {
        val itemVar = temp("item")
        val element = genValue(type.element, scope, CodeBlock.of("%N", itemVar), nameHint)
            ?: throw UnsupportedProtoException("array of void")

        val read = CodeBlock.builder()
        read.add("List(").add(countRead(type.countType, type.count, scope)).add(") {\n")
        read.indent()
        read.add(element.read).add("\n")
        read.unindent()
        read.add("}")

        val write = CodeBlock.builder()
        countWrite(type.countType, CodeBlock.of("%L.size", writeAccess))?.let { write.add(it) }
        write.add("for (%N in %L) {\n", itemVar, writeAccess)
        write.indent()
        write.add(element.write)
        write.unindent()
        write.add("}\n")

        return ValueCode(LIST.parameterizedBy(element.typeName), read.build(), write.build())
    }

    private fun genOption(type: OptionType, scope: Scope, writeAccess: CodeBlock, nameHint: String): ValueCode {
        val optVar = temp("opt")
        val inner = genValue(type.inner, scope, CodeBlock.of("%N", optVar), nameHint)
            ?: throw UnsupportedProtoException("option of void")

        val read = CodeBlock.builder()
        read.add("if·(input.readBoolean())·").add(inner.read).add("·else·null")

        val write = CodeBlock.builder()
        write.add("val %N = ", optVar).add(writeAccess).add("\n")
        write.add("output.writeBoolean(%N·!=·null)\n", optVar)
        write.add("if (%N != null) {\n", optVar)
        write.indent()
        write.add(inner.write)
        write.unindent()
        write.add("}\n")

        return ValueCode(inner.typeName.copy(nullable = true), read.build(), write.build(), inner.compare)
    }

    private fun genBuffer(type: BufferType, scope: Scope, writeAccess: CodeBlock): ValueCode {
        val countTypeName = type.countType?.let { (resolver.deepResolve(it) as? NativeType)?.name }

        if (countTypeName == "varint") {
            return ValueCode(
                typeName = BYTE_ARRAY,
                read = CodeBlock.of("input.readVarIntByteArray()"),
                write = CodeBlock.builder().add("output.writeVarIntByteArray(%L)\n", writeAccess).build(),
            )
        }

        val read = CodeBlock.builder()
            .add("input.readBytes(")
            .add(countRead(type.countType, type.count, scope))
            .add(")")
            .build()

        val write = CodeBlock.builder()
        countWrite(type.countType, CodeBlock.of("%L.size", writeAccess))?.let { write.add(it) }
        write.add("output.writeBytes(%L)\n", writeAccess)

        return ValueCode(BYTE_ARRAY, read, write.build())
    }

    private fun genPString(type: PStringType, writeAccess: CodeBlock): ValueCode {
        val countTypeName = type.countType?.let { (resolver.deepResolve(it) as? NativeType)?.name }
            ?: throw UnsupportedProtoException("pstring without countType")
        if (countTypeName != "varint") throw UnsupportedProtoException("pstring with countType $countTypeName")

        return ValueCode(
            typeName = STRING,
            read = CodeBlock.of("input.readString()"),
            write = CodeBlock.builder().add("output.writeString(%L)\n", writeAccess).build(),
            compare = CompareInfo(CompareKind.STRING),
        )
    }

    // ---------------------------------------------------------------------------------------
    // Switches
    // ---------------------------------------------------------------------------------------

    private fun lookup(scope: Scope, rawPath: String): ScopeEntry {
        if (rawPath.startsWith("$")) throw UnsupportedProtoException("template compareTo '$rawPath'")
        var path = rawPath
        var start: Scope? = scope
        while (path.startsWith("../")) {
            start = start?.parent ?: throw UnsupportedProtoException("compareTo '$rawPath' escapes the packet")
            path = path.removePrefix("../")
        }
        if ('/' in path) throw UnsupportedProtoException("compareTo path '$rawPath'")

        var current: Scope? = start
        while (current != null) {
            current.entries[path]?.let { return it }
            current = current.parent
        }
        throw UnsupportedProtoException("compareTo field '$rawPath' not found")
    }

    private fun compareSubject(entry: ScopeEntry, read: Boolean): CodeBlock {
        val ref = if (read) entry.readRef else entry.writeRef
        val compare = entry.compare ?: throw UnsupportedProtoException("compareTo field is not comparable")
        val conversion = if (entry.nullable && compare.conversion.isNotEmpty()) "?${compare.conversion}" else compare.conversion
        return CodeBlock.of("%L%L", ref, conversion)
    }

    private fun caseLiteral(kind: CompareKind, key: String): CodeBlock = when (kind) {
        CompareKind.INT -> CodeBlock.of("%L", parseIntKey(key))
        CompareKind.LONG -> CodeBlock.of("%LL", parseIntKey(key))
        CompareKind.STRING -> CodeBlock.of("%S", key)
        CompareKind.BOOL -> when (key) {
            "true", "false" -> CodeBlock.of("%L", key)
            else -> throw UnsupportedProtoException("boolean switch key '$key'")
        }
    }

    private fun parseIntKey(key: String): Long = when {
        key.startsWith("0x") || key.startsWith("0X") -> key.substring(2).toLong(16)
        else -> key.toLongOrNull() ?: throw UnsupportedProtoException("non-numeric switch key '$key'")
    }

    private fun isVoid(type: ProtoType): Boolean = resolver.deepResolve(type) is VoidType

    private fun genSwitch(type: SwitchType, scope: Scope, writeAccess: CodeBlock, nameHint: String): ValueCode? {
        val entry = lookup(scope, type.compareTo)
        val compare = entry.compare ?: throw UnsupportedProtoException("compareTo '${type.compareTo}' is not comparable")

        val voidCases = type.cases.filterValues { isVoid(it) }
        val valueCases = type.cases.filterValues { !isVoid(it) }
        val default = type.default?.takeUnless { isVoid(it) }

        if (valueCases.isEmpty() && default == null) return null // no data in any branch

        val boolExhaustive = compare.kind == CompareKind.BOOL &&
            type.cases.keys == setOf("true", "false") &&
            voidCases.isEmpty() &&
            type.default == null
        val exhaustive = boolExhaustive || (default != null && voidCases.isEmpty())
        val nullable = !exhaustive

        val innerAccess = if (nullable) CodeBlock.of("%L!!", writeAccess) else writeAccess

        val generated = valueCases.mapValues { (_, caseType) ->
            genValue(caseType, scope, innerAccess, nameHint)
                ?: throw UnsupportedProtoException("switch case became void")
        }
        val defaultGenerated = default?.let {
            genValue(it, scope, innerAccess, nameHint) ?: throw UnsupportedProtoException("switch default became void")
        }

        val typeNames = (generated.values + listOfNotNull(defaultGenerated)).map { it.typeName.copy(nullable = false) }.toSet()
        if (typeNames.size != 1) throw UnsupportedProtoException("heterogeneous switch branches: $typeNames")
        val branchType = typeNames.first()

        val read = CodeBlock.builder()
        read.add("when·(").add(compareSubject(entry, read = true)).add(")·{\n")
        read.indent()
        for ((key, value) in generated) {
            read.add(caseLiteral(compare.kind, key)).add(" -> ").add(value.read).add("\n")
        }
        if (default != null) {
            for (key in voidCases.keys) {
                read.add(caseLiteral(compare.kind, key)).add(" -> null\n")
            }
        }
        when {
            boolExhaustive -> Unit
            defaultGenerated != null -> read.add("else -> ").add(defaultGenerated.read).add("\n")
            else -> read.add("else -> null\n")
        }
        read.unindent()
        read.add("}")

        val write = CodeBlock.builder()
        write.add("when·(").add(compareSubject(entry, read = false)).add(")·{\n")
        write.indent()
        for ((key, value) in generated) {
            write.add(caseLiteral(compare.kind, key)).add(" -> {\n")
            write.indent()
            write.add(value.write)
            write.unindent()
            write.add("}\n")
        }
        if (defaultGenerated != null) {
            for (key in voidCases.keys) {
                write.add(caseLiteral(compare.kind, key)).add(" -> {}\n")
            }
            write.add("else -> {\n")
            write.indent()
            write.add(defaultGenerated.write)
            write.unindent()
            write.add("}\n")
        } else if (!boolExhaustive) {
            write.add("else -> {}\n")
        }
        write.unindent()
        write.add("}\n")

        val resultNullable = nullable || generated.values.any { it.typeName.isNullable } || defaultGenerated?.typeName?.isNullable == true

        val branchCompares = (generated.values + listOfNotNull(defaultGenerated)).map { it.compare }.toSet()
        val resultCompare = branchCompares.singleOrNull()

        return ValueCode(branchType.copy(nullable = resultNullable), read.build(), write.build(), resultCompare)
    }

    // ---------------------------------------------------------------------------------------
    // Bitfields & bitflags
    // ---------------------------------------------------------------------------------------

    private data class RawIntCodec(val read: String, val writeFormat: String)

    private fun rawCodecForBits(totalBits: Int): RawIntCodec = when (totalBits) {
        8 -> RawIntCodec("input.readByte().toLong()", "output.writeByte(%L.toByte())")
        16 -> RawIntCodec("input.readShort().toLong()", "output.writeShort(%L.toShort())")
        32 -> RawIntCodec("input.readInt().toLong()", "output.writeInt(%L.toInt())")
        64 -> RawIntCodec("input.readLong()", "output.writeLong(%L)")
        else -> throw UnsupportedProtoException("bitfield of $totalBits bits")
    }

    private fun genBitfield(type: BitfieldType, writeAccess: CodeBlock, nameHint: String): ValueCode {
        val totalBits = type.entries.sumOf { it.size }
        val codec = rawCodecForBits(totalBits)

        val className = fileClassNames.claim(nameHint)
        val classType = ClassName(packageName, className)

        val classNames = UniqueNames().apply { claim("input"); claim("output"); claim("value") }
        data class Entry(val prop: String, val typeName: TypeName, val shift: Int, val size: Int, val signed: Boolean, val protoName: String)

        var offset = 0
        val entries = type.entries.map { e ->
            val shift = totalBits - offset - e.size
            offset += e.size
            val typeName: TypeName = when {
                e.size == 1 -> com.squareup.kotlinpoet.BOOLEAN
                e.size <= 32 -> com.squareup.kotlinpoet.INT
                else -> com.squareup.kotlinpoet.LONG
            }
            Entry(classNames.claim(camelCase(e.name)), typeName, shift, e.size, e.signed, e.name)
        }

        extraTypes += buildDataClass(classType, entries.map { PropInfo(it.prop, it.typeName, "${it.protoName} (${it.size} bits)") })

        fun mask(size: Int): String = "0x" + java.lang.Long.toHexString(if (size >= 64) -1L else (1L shl size) - 1).uppercase() + "L"

        val rawVar = temp("raw")
        val read = CodeBlock.builder()
        read.add("run {\n")
        read.indent()
        read.add("val %N = %L\n", rawVar, codec.read)
        read.add("%T(\n", classType)
        read.indent()
        for (e in entries) {
            val expr = when {
                e.size == 1 -> CodeBlock.of("((%N·ushr·%L)·and·1L)·==·1L", rawVar, e.shift)
                e.size == 64 -> CodeBlock.of("%N", rawVar)
                e.signed -> {
                    val toType = if (e.size <= 32) ".toInt()" else ""
                    CodeBlock.of("((%N·shl·%L)·shr·%L)%L", rawVar, 64 - e.shift - e.size, 64 - e.size, toType)
                }
                else -> {
                    val toType = if (e.size <= 32) ".toInt()" else ""
                    CodeBlock.of("((%N·ushr·%L)·and·%L)%L", rawVar, e.shift, mask(e.size), toType)
                }
            }
            read.add(expr).add(",\n")
        }
        read.unindent()
        read.add(")\n")
        read.unindent()
        read.add("}")

        val outVar = temp("raw")
        val write = CodeBlock.builder()
        write.add("var %N = 0L\n", outVar)
        for (e in entries) {
            when {
                e.size == 1 ->
                    write.add("if (%L.%N) %N = %N·or·(1L·shl·%L)\n", writeAccess, e.prop, outVar, outVar, e.shift)
                e.size == 64 ->
                    write.add("%N = %L.%N\n", outVar, writeAccess, e.prop)
                else -> {
                    val toLong = if (e.typeName == com.squareup.kotlinpoet.INT) ".toLong()" else ""
                    write.add(
                        "%N = %N·or·((%L.%N%L·and·%L)·shl·%L)\n",
                        outVar, outVar, writeAccess, e.prop, toLong, mask(e.size), e.shift,
                    )
                }
            }
        }
        write.add(codec.writeFormat, CodeBlock.of("%N", outVar)).add("\n")

        return ValueCode(classType, read.build(), write.build())
    }

    private fun genBitflags(type: BitflagsType, writeAccess: CodeBlock, nameHint: String): ValueCode {
        val underlyingName = (resolver.deepResolve(type.underlying) as? NativeType)?.name
            ?: throw UnsupportedProtoException("bitflags with non-native underlying type")
        val codec = longViaCodec(underlyingName)

        val className = fileClassNames.claim(nameHint)
        val classType = ClassName(packageName, className)

        val classNames = UniqueNames().apply { claim("input"); claim("output"); claim("value") }
        val props = type.flags.map { classNames.claim(camelCase(it)) }

        extraTypes += buildDataClass(
            classType,
            props.mapIndexed { i, p -> PropInfo(p, com.squareup.kotlinpoet.BOOLEAN, "${type.flags[i]} (bit ${i})") },
        )

        val rawVar = temp("raw")
        val read = CodeBlock.builder()
        read.add("run {\n")
        read.indent()
        read.add("val %N = ", rawVar).add(codec.read).add("\n")
        read.add("%T(\n", classType)
        read.indent()
        for (i in props.indices) {
            read.add("(%N·and·0x%LL)·!=·0L,\n", rawVar, java.lang.Long.toHexString(1L shl i).uppercase())
        }
        read.unindent()
        read.add(")\n")
        read.unindent()
        read.add("}")

        val outVar = temp("raw")
        val write = CodeBlock.builder()
        write.add("var %N = 0L\n", outVar)
        for (i in props.indices) {
            write.add(
                "if (%L.%N) %N = %N·or·0x%LL\n",
                writeAccess, props[i], outVar, outVar, java.lang.Long.toHexString(1L shl i).uppercase(),
            )
        }
        write.add(codec.writeFormat, CodeBlock.of("%N", outVar)).add("\n")

        return ValueCode(classType, read.build(), write.build())
    }

    // ---------------------------------------------------------------------------------------
    // Mappers
    // ---------------------------------------------------------------------------------------

    private fun genMapper(type: MapperType, writeAccess: CodeBlock): ValueCode {
        val underlying = resolver.deepResolve(type.underlying) as? NativeType
            ?: throw UnsupportedProtoException("mapper with non-native underlying type")

        if (underlying.name == "string") {
            val read = CodeBlock.builder()
            read.add("when·(input.readString())·{\n")
            read.indent()
            for ((key, value) in type.mappings) read.add("%S -> %S\n", key, value)
            read.add("else -> throw·IllegalArgumentException(%S)\n", "Unknown mapper value")
            read.unindent()
            read.add("}")

            val write = CodeBlock.builder()
            write.add("output.writeString(when·(%L)·{\n", writeAccess)
            write.indent()
            for ((key, value) in type.mappings) write.add("%S -> %S\n", value, key)
            write.add("else -> throw·IllegalArgumentException(%S)\n", "Unknown mapper value")
            write.unindent()
            write.add("})\n")

            return ValueCode(STRING, read.build(), write.build(), CompareInfo(CompareKind.STRING))
        }

        val codec = longViaCodec(underlying.name)

        val read = CodeBlock.builder()
        read.add("when·(").add(codec.read).add(")·{\n")
        read.indent()
        for ((key, value) in type.mappings) read.add("%LL -> %S\n", parseIntKey(key), value)
        read.add("else -> throw·IllegalArgumentException(%S)\n", "Unknown mapper value")
        read.unindent()
        read.add("}")

        val write = CodeBlock.builder()
        val whenBlock = CodeBlock.builder()
        whenBlock.add("when·(%L)·{\n", writeAccess)
        whenBlock.indent()
        for ((key, value) in type.mappings) whenBlock.add("%S -> %LL\n", value, parseIntKey(key))
        whenBlock.add("else -> throw·IllegalArgumentException(%S)\n", "Unknown mapper value")
        whenBlock.unindent()
        whenBlock.add("}")
        write.add(codec.writeFormat, whenBlock.build()).add("\n")

        return ValueCode(STRING, read.build(), write.build(), CompareInfo(CompareKind.STRING))
    }
}
