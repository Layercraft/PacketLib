package fields

import PacketData
import com.squareup.kotlinpoet.PropertySpec
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.util.regex.Pattern

interface Field {
    fun addClassFields(fieldInfo: FieldInfo): List<PropertySpec>
    fun addClassSerialize(fieldInfo: FieldInfo): List<String>
    fun addClassDeserialize(fieldInfo: FieldInfo): List<String>
    fun addClassVarList(fieldInfo: FieldInfo): List<String>
    fun addClassDocs(fieldInfo: FieldInfo): List<String>
}

data class FieldInfo(
    val jsonObject: JsonObject,
    val name: String = jsonObject["name"]!!.jsonPrimitive.content,
    val typeJson: JsonElement = jsonObject["type"]!!,
    val varName: String = camelCase(name),
    val packet: PacketData,
    val layer: Int = 0,
    val optional: Boolean = false,
    val serializerVariableName: String = "output",
    val deserializerVariableName: String = "input",
)

fun camelCase(s: String): String {
    val p = Pattern.compile("([-_])([a-zA-Z])")
    val m = p.matcher(s)
    val sb = StringBuilder()
    while (m.find()) {
        m.appendReplacement(sb, m.group(2).toUpperCase())
    }
    m.appendTail(sb)
    val camelCaseStr = sb.toString()
    return camelCaseStr[0].lowercaseChar() + camelCaseStr.substring(1)
}

@Target(AnnotationTarget.CLASS)
annotation class FieldType(val name: String)