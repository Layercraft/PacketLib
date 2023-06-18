package fields

import com.squareup.kotlinpoet.ParameterSpec

@FieldType("test-field")
object TestField: Field{
    override fun addClassFields(fieldInfo: FieldInfo): List<ParameterSpec> {
        TODO("Not yet implemented")
    }

    override fun addClassSerialize(fieldInfo: FieldInfo): List<String> {
        TODO("Not yet implemented")
    }

    override fun addClassDeserialize(fieldInfo: FieldInfo): List<String> {
        TODO("Not yet implemented")
    }

    override fun addClassVarList(fieldInfo: FieldInfo): List<String> {
        TODO("Not yet implemented")
    }

    override fun addClassDocs(fieldInfo: FieldInfo): List<String> {
        TODO("Not yet implemented")
    }

}