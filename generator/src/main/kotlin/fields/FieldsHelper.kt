package fields

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonPrimitive

object FieldsHelper {

    private val fields: Map<String, Field> = register()

    fun register(): Map<String, Field> {
        val classes: List<Field> = listOf(
            TestField,
        ) + BasicFields.ALL

        val fieldTypes = classes.associateBy { it::class.java.getAnnotation(FieldType::class.java)?: error("Field ${it::class.java.name} has no FieldType annotation") }

        println("Found ${fieldTypes.size} field types with names: ${fieldTypes.keys.map { it.name }}")

        return fieldTypes.mapKeys { it.key.name }
    }

    fun get(name: String): Field {
        return fields[name] ?: error("Field $name not found")
    }

    fun generateField(ctx: GenerationContext, fieldInfo: FieldInfo) {
        if (fieldInfo.typeJson is JsonPrimitive) {
            val type = fieldInfo.typeJson.content
            val field = get(type)

            ctx.packetClass.addProperties(field.addClassFields(fieldInfo))



        } else if (fieldInfo.typeJson is JsonArray) {
            val type = fieldInfo.typeJson[0].jsonPrimitive.content

            if (type == "option") {
                generateField(ctx, fieldInfo.copy(
                    typeJson = fieldInfo.typeJson[1],
                    optional = true,
                ))
                return
            }
            val field = get(type)
        }
    }
}

data class GenerationContext(
    val file: FileSpec.Builder,
    val packetClass: TypeSpec.Builder,
)