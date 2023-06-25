package fields

import PacketData
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject

object FieldsHelper {

    private lateinit var fields: Map<String, Field>

    fun register(): Map<String, Field> {
        val classes: List<Field> = listOf(
            TestField,
        ) + BasicFields.ALL

        val fieldTypes = classes.associateBy { it::class.java.getAnnotation(FieldType::class.java) ?: error("Field ${it::class.java.name} has no FieldType annotation") }

        fields = fieldTypes.mapKeys { it.key.name }

        return fields
    }

    fun get(name: String): Field {
        return fields[name] ?: error("Field $name not found")
    }

    fun generateClass(ctx: GenerationContext, packetData: PacketData) {
        val constructorBuilder = FunSpec.constructorBuilder()

        for (fieldJson in packetData.fields) {
            val fieldInfo = generateFieldInfo(packetData, fieldJson.jsonObject)
            if (fieldInfo.typeJson is JsonPrimitive) {
                val type = fieldInfo.typeJson.content
                val field = get(type)

                val classFields = field.addClassFields(fieldInfo)

                classFields.forEach {
                    constructorBuilder.addParameter(it)
                    ctx.packetClass.addProperty(PropertySpec.builder(it.name, it.type).initializer(it.name).build())
                }
            } else if (fieldInfo.typeJson is JsonArray) {
                TODO()
            }
        }

        val constructor = constructorBuilder.build()
        ctx.packetClass.primaryConstructor(constructor)
    }

    private fun generateFieldInfo(packetData: PacketData, jsonObject: JsonObject): FieldInfo {
        return FieldInfo(
            jsonObject = jsonObject,
            packet = packetData,
        )
    }
}

data class GenerationContext(
    val file: FileSpec.Builder,
    val packetClass: TypeSpec.Builder,
)