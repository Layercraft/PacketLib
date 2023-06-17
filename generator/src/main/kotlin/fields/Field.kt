package fields

import com.squareup.kotlinpoet.PropertySpec

interface Field {
    fun addClassFields(): List<PropertySpec>
    fun addClassSerialize(): List<String>
    fun addClassDeserialize(): List<String>
    fun addClassVarList(): List<String>
    fun addClassDocs(): List<String>
}