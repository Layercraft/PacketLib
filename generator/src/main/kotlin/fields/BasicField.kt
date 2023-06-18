package fields

import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.asTypeName
import kotlin.reflect.KClass

abstract class BasicField(
    private val clazz: KClass<*>,
    private val serialize: String,
    private val deserialize: String,
    private val classVarListComment: String = "",
    private val optional: Boolean = false,
) : Field {

    override fun addClassFields(fieldInfo: FieldInfo): List<PropertySpec> {
        return listOf(PropertySpec.Companion.builder(fieldInfo.varName, clazz::class.asTypeName().copy(nullable = optional)).build())
    }

    override fun addClassSerialize(fieldInfo: FieldInfo): List<String> {
        return listOf(
            "${fieldInfo.serializerVariableName}.${serialize.replace("%s", fieldInfo.varName)}"
        )
    }

    override fun addClassDeserialize(fieldInfo: FieldInfo): List<String> {
        return listOf(
            "${fieldInfo.name} = ${fieldInfo.deserializerVariableName}.${deserialize.replace("%s", fieldInfo.varName)}"
        )
    }

    override fun addClassVarList(fieldInfo: FieldInfo): List<String> {
        return listOf(
            "val ${fieldInfo.name}: ${clazz.simpleName}, // $classVarListComment"
        )
    }

    override fun addClassDocs(fieldInfo: FieldInfo): List<String> {
        return listOf(
            "@param ${fieldInfo.varName} ${fieldInfo.name}",
        )
    }
}