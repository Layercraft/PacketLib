package fields

import kotlin.reflect.KClass

enum class KotlinTypes(
    val clazz: KClass<*>,
    val optional: Boolean,
    val deserializeMethod: String,
    val serializeMethod: String,
) : Field