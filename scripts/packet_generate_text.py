import re
from typing import Optional, Any

kotlin_types_wrapper = {
    "varint": {
        "type": "Int",
        "deserialize": "readVarInt()",
        "serialize": "writeVarInt(%s)",
        "comment": "varint"
    },
    "optvarint": {
        "type": "Int?",
        "deserialize": "readVarInt()",
        "serialize": "writeVarInt(%s)"
    },
    "i8": {
        "type": "Byte",
        "deserialize": "readByte()",
        "serialize": "writeByte(%s)"
    },
    "u8": {
        "type": "UByte",
        "deserialize": "readUByte()",
        "serialize": "writeUByte(%s)"
    },
    "i16": {
        "type": "Short",
        "deserialize": "readShort()",
        "serialize": "writeShort(%s)"
    },
    "u16": {
        "type": "UShort",
        "deserialize": "readUShort()",
        "serialize": "writeUShort(%s)"
    },
    "i32": {
        "type": "Int",
        "deserialize": "readInt()",
        "serialize": "writeInt(%s)"
    },
    "i64": {
        "type": "Long",
        "deserialize": "readLong()",
        "serialize": "writeLong(%s)"
    },
    "bool": {
        "type": "Boolean",
        "deserialize": "readBoolean()",
        "serialize": "writeBoolean(%s)"
    },
    "f32": {
        "type": "Float",
        "deserialize": "readFloat()",
        "serialize": "writeFloat(%s)"
    },
    "f64": {
        "type": "Double",
        "deserialize": "readDouble()",
        "serialize": "writeDouble(%s)"
    },
    "UUID": {
        "type": "UUID",
        "deserialize": "readUUID()",
        "serialize": "writeUUID(%s)",
        "import": "import java.util.UUID"
    },
    "string": {
        "type": "String",
        "deserialize": "readString()",
        "serialize": "writeString(%s)"
    },
    "void": {
        "type": "null",
        "deserialize": "null",
        "serialize": "null"
    },
    "buffer": {
        "type": "ByteArray",
        "deserialize": "readVarIntByteArray()",
        "serialize": "writeVarIntByteArray(%s)"
    },
    "restBuffer": {
        "type": "ByteArray",
        "deserialize": "readRemainingByteArray()",
        "serialize": "writeRemainingByteArray(%s)"
    },
    "nbt": {
        "type": "NBT",
        "deserialize": "readNbt()",
        "serialize": "writeNbt(%s)",
        "import": "import io.layercraft.packetlib.types.NBT"
    },
    "optionalNbt": {
        "type": "NBT",
        "deserialize": "readNbt()",
        "serialize": "writeNbt(%s)",
        "import": "import io.layercraft.packetlib.types.NBT"
    },
    "position": {
        "type": "Position",
        "deserialize": "readPosition()",
        "serialize": "writePosition(%s)",
        "import": "import io.layercraft.packetlib.types.Position"
    },
    "chunkBlockEntity": {
        "type": "ChunkBlockEntity",
        "deserialize": "readChunkBlockEntity()",
        "serialize": "writeChunkBlockEntity(%s)",
        "import": "import io.layercraft.packetlib.types.ChunkBlockEntity"
    },
    "entityMetadataLoop": "native",
    "topBitSetTerminatedArray": "native",
    "bitfield": "own_impl",
    "slot": "native",
    "entityMetadata": "native",
    "previousMessages": "native",
    "command_node": "native",
    "tags": "native",
    "minecraft_smelting_format": "native",
    "ingredient": "native",
}


def camel_case(s):
    camel_case_str = re.sub(r"([-_])([a-zA-Z])",
                            lambda m: m.group(2).upper(), s)
    camel_case_str = camel_case_str[0].lower() + camel_case_str[1:]
    return camel_case_str


class PacketGeneratorNew:
    def __init__(self, packet_dict: dict):
        self.packet = packet_dict
        self.id = packet_dict['id']
        self.name = packet_dict['name']
        self.direction = packet_dict['direction']
        self.status = packet_dict["status"]
        self.fields = packet_dict['fields']
        self.class_name = self.name.replace("_", " ").title().replace(" ", "") + "Packet"
        self.internal_list = []

    def add_to_clazz_field(self, return_value: dict, clazz: dict):
        if clazz is None:
            raise Exception("clazz is None")

        if return_value is not None:
            if "fields" in return_value:
                clazz["fields"] += return_value["fields"]
                self.internal_list += return_value["fields"]
            if "serialize" in return_value:
                clazz["serialize"] += return_value["serialize"]
            if "deserialize" in return_value:
                clazz["deserialize"] += return_value["deserialize"]
            if "var_list" in return_value:
                clazz["var_list"] += return_value["var_list"]
            if "docs" in return_value:
                clazz["docs"] += return_value["docs"]
            if "other_imports" in return_value:
                clazz["other_imports"] += return_value["other_imports"]
            if "extra_classes" in return_value:
                clazz["extra_classes"] += return_value["extra_classes"]

    def generate_basic_type(self, field_name: str, field_type: str, infos: dict) -> Optional[dict[str, list[Any]]]:
        class_fields = []
        class_serialize = []
        class_deserialize = []
        class_var_list = []
        class_docs = []
        class_other_imports = []

        info_deserialize_var_name = infos["deserialize_var_name"] if "deserialize_var_name" in infos else "input"
        info_serialize_var_name = infos["serialize_var_name"] if "serialize_var_name" in infos else "output"
        info_serialize_value_var_type = infos["serialize_value_var_type"] if "serialize_value_var_type" in infos else "value"

        if field_name.upper() == "UUID":
            field_name = "uuid"

        field_var_name = camel_case(field_name)

        kotlin_type = kotlin_types_wrapper[field_type]
        if kotlin_type == "native" or field_type == "void":
            return None

        kotlin_type_str = kotlin_type["type"]
        kotlin_type_serialize = kotlin_type["serialize"]
        kotlin_type_deserialize = kotlin_type["deserialize"]

        field_comment = " // " + kotlin_type["comment"] if "comment" in kotlin_type else ""

        class_fields += [f"val {field_var_name}: {kotlin_type_str},{field_comment}"]

        class_deserialize += [f"val {field_var_name} = {info_deserialize_var_name}.{kotlin_type_deserialize}"]
        class_serialize += [f"{info_serialize_var_name}.{kotlin_type_serialize % (info_serialize_value_var_type + '.' + field_var_name)}"]

        class_var_list += [field_var_name]
        class_docs += [f" * @param {field_var_name} {field_name}"]

        if "import" in kotlin_type:
            class_other_imports += [kotlin_type["import"]]

        return {
            "fields": class_fields,
            "serialize": class_serialize,
            "deserialize": class_deserialize,
            "var_list": class_var_list,
            "docs": class_docs,
            "other_imports": class_other_imports,
            "extra_classes": []
        }

    def generate_option_basic_type(self, field_name: str, field_type: str, infos: dict) -> Optional[dict[str, list[Any]]]:
        class_fields = []
        class_serialize = []
        class_deserialize = []
        class_var_list = []
        class_docs = []
        class_other_imports = []

        info_deserialize_var_name = infos["deserialize_var_name"] if "deserialize_var_name" in infos else "input"
        info_serialize_var_name = infos["serialize_var_name"] if "serialize_var_name" in infos else "output"
        info_serialize_value_var_type = infos["serialize_value_var_type"] if "serialize_value_var_type" in infos else "value"

        if field_name.upper() == "UUID":
            field_name = "uuid"

        field_var_name = camel_case(field_name)

        kotlin_type = kotlin_types_wrapper[field_type]
        if kotlin_type == "native":
            return None

        boolean_field_var_name = ("has" + field_var_name[0].upper() + field_var_name[1:])

        class_fields += [f"val {boolean_field_var_name}: Boolean,"]
        class_deserialize += [f"val {boolean_field_var_name} = {info_deserialize_var_name}.readBoolean()"]
        class_serialize += [f"{info_serialize_var_name}.writeBoolean({info_serialize_value_var_type}.{boolean_field_var_name})"]
        class_var_list += [boolean_field_var_name]
        class_docs += [f" * @param {boolean_field_var_name} {field_name} is present"]

        return_value = self.generate_option_basic_type_custom(field_name, field_type, boolean_field_var_name, infos)
        if return_value is not None:
            class_fields += return_value["fields"]
            class_serialize += return_value["serialize"]
            class_deserialize += return_value["deserialize"]
            class_var_list += return_value["var_list"]
            class_docs += return_value["docs"]
            class_other_imports += return_value["other_imports"]

        return {
            "fields": class_fields,
            "serialize": class_serialize,
            "deserialize": class_deserialize,
            "var_list": class_var_list,
            "docs": class_docs,
            "other_imports": class_other_imports,
            "extra_classes": []
        }

    def generate_option_basic_type_custom(self, field_name: str, field_type: str, boolean_field_var_name: str, infos: dict) -> Optional[dict[str, list[Any]]]:
        class_fields = []
        class_serialize = []
        class_deserialize = []
        class_var_list = []
        class_docs = []
        class_other_imports = []

        info_deserialize_var_name = infos["deserialize_var_name"] if "deserialize_var_name" in infos else "input"
        info_serialize_var_name = infos["serialize_var_name"] if "serialize_var_name" in infos else "output"
        info_serialize_value_var_type = infos["serialize_value_var_type"] if "serialize_value_var_type" in infos else "value"

        if field_name.upper() == "UUID":
            field_name = "uuid"

        field_var_name = camel_case(field_name)

        kotlin_type = kotlin_types_wrapper[field_type]
        if kotlin_type == "native":
            return None

        kotlin_type_str = kotlin_type["type"]
        kotlin_type_serialize = kotlin_type["serialize"]
        kotlin_type_deserialize = kotlin_type["deserialize"]

        field_comment = " // " + kotlin_type["comment"] if "comment" in kotlin_type else ""

        class_fields += [f"val {field_var_name}: {kotlin_type_str}?,{field_comment}"]

        class_deserialize += [f"val {field_var_name} = if ({boolean_field_var_name}) {info_deserialize_var_name}.{kotlin_type_deserialize} else null"]
        class_serialize += [f"if ({info_serialize_value_var_type}.{boolean_field_var_name}) {info_serialize_var_name}.{kotlin_type_serialize % (info_serialize_value_var_type + '.' + field_var_name + '!!')}"]

        class_var_list += [field_var_name]
        class_docs += [f" * @param {field_var_name} {field_name}"]

        if "import" in kotlin_type:
            class_other_imports += [kotlin_type["import"]]

        return {
            "fields": class_fields,
            "serialize": class_serialize,
            "deserialize": class_deserialize,
            "var_list": class_var_list,
            "docs": class_docs,
            "other_imports": class_other_imports,
            "extra_classes": []
        }

    def generate_bitfield(self, field_name: str, field_data: list, infos: dict) -> dict[str, list[Any]]:
        class_fields = []
        class_serialize = []
        class_deserialize = []
        class_var_list = []
        class_docs = []
        class_other_imports = []

        field_var_name = camel_case(field_name)

        info_deserialize_var_name = infos["deserialize_var_name"] if "deserialize_var_name" in infos else "input"
        info_serialize_var_name = infos["serialize_var_name"] if "serialize_var_name" in infos else "output"
        info_serialize_value_var_type = infos["serialize_value_var_type"] if "serialize_value_var_type" in infos else "value"

        bytes = 0
        docs = []
        for bitfield in field_data:
            bytes += bitfield["size"]
            docs += [f"Name: {bitfield['name']}, Size: {bitfield['size']}, Signed: {bitfield['signed']}"]

        class_fields += [f"val {field_var_name}: Bitfield,"]
        class_deserialize += [f"val {field_var_name} = Bitfield.valueOf({info_deserialize_var_name}.readBytes({bytes}))"]
        class_serialize += [f"{info_serialize_var_name}.writeBytes({info_serialize_value_var_type}.{field_var_name}.toByteArray())"]
        class_var_list += [field_var_name]
        docs_str = " | ".join(docs)
        class_docs += [f" * @param {field_var_name} {field_name} ({docs_str})"]
        class_other_imports = ["import io.layercraft.packetlib.types.Bitfield"]

        return {
            "fields": class_fields,
            "serialize": class_serialize,
            "deserialize": class_deserialize,
            "var_list": class_var_list,
            "docs": class_docs,
            "other_imports": class_other_imports,
            "extra_classes": []
        }

    def generate_basic_type_array(self, field_name: str, array_type: str, infos: dict) -> Optional[dict[str, list[Any]]]:
        class_fields = []
        class_serialize = []
        class_deserialize = []
        class_var_list = []
        class_docs = []
        class_other_imports = []

        info_deserialize_var_name = infos["deserialize_var_name"] if "deserialize_var_name" in infos else "input"
        info_serialize_var_name = infos["serialize_var_name"] if "serialize_var_name" in infos else "output"
        info_serialize_value_var_type = infos["serialize_value_var_type"] if "serialize_value_var_type" in infos else "value"

        field_var_name = camel_case(field_name)

        kotlin_type = kotlin_types_wrapper[array_type]
        if kotlin_type == "native":
            return None

        kotlin_type_str = kotlin_type["type"]
        kotlin_type_serialize = kotlin_type["serialize"]
        kotlin_type_deserialize = kotlin_type["deserialize"]

        field_comment = " of " + kotlin_type["comment"] if "comment" in kotlin_type else ""

        class_fields += [f"val {field_var_name}: List<{kotlin_type_str}>, // varint array{field_comment}"]
        class_deserialize += [f"val {field_var_name} = {info_deserialize_var_name}.readVarIntArray {{ arrayInput -> arrayInput.{kotlin_type_deserialize} }}"]
        class_serialize += [f"{info_serialize_var_name}.writeVarIntArray({info_serialize_value_var_type}.{field_var_name}) {{ arrayValue, arrayOutput -> arrayOutput.{kotlin_type_serialize % 'arrayValue'} }}"]
        class_var_list += [field_var_name]
        class_docs += [f" * @param {field_var_name} {field_name}"]

        if "import" in kotlin_type:
            class_other_imports += [kotlin_type["import"]]

        return {
            "fields": class_fields,
            "serialize": class_serialize,
            "deserialize": class_deserialize,
            "var_list": class_var_list,
            "docs": class_docs,
            "other_imports": class_other_imports,
            "extra_classes": []
        }

    def generate_extra_class(self, class_name: str, class_fields: list, class_docs: list) -> str:
        class_docs_str = "\n".join(class_docs)
        class_fields_str = "\n    ".join(class_fields)
        class_str = f"""
/**
 * {class_name}
 *
{class_docs_str}
*/
data class {class_name}(
    {class_fields_str}
)
        """
        return class_str

    def generate_extra_class_serializer(self, field_name: str, info_deserialize_var_name: str, info_serialize_value_var_type: str, serialization_list: list, round: int = 0) -> str:
        extra_class_serialize_str = "\n                 ".join(serialization_list)
        round_str = f"{round}" if round > 0 else ""
        class_serialize_str = f"""
                {info_deserialize_var_name}.writeVarIntArray({info_serialize_value_var_type}.{field_name}) {{ arrayValue{round_str}, arrayOutput{round_str} ->
                {extra_class_serialize_str}
            }}
        """
        return class_serialize_str

    def generate_extra_class_deserializer(self, field_name: str, info_deserialize_var_name: str, deserialization_list: list, fields: list, class_name: str, round: int = 0) -> str:
        extra_class_deserialize_str = "\n                   ".join(deserialization_list)
        extra_class_fields_str = ", ".join(fields)
        round_str = f"{round}" if round > 0 else ""

        class_deserialize_str = f"""val {field_name} = {info_deserialize_var_name}.readVarIntArray {{ arrayInput{round_str} ->
                {extra_class_deserialize_str}

                {class_name}({extra_class_fields_str})
            }}"""

        return class_deserialize_str

    def generate_container_array(self, field_name: str, array: dict, infos: dict, round: int = 0) -> dict:
        count_type = array["countType"]
        if count_type != "varint":
            raise Exception("Not supported")

        array_type = array["type"]
        if array_type[0] != "container":
            raise Exception("Not supported")

        info_deserialize_var_name = infos["deserialize_var_name"] if "deserialize_var_name" in infos else "input"
        info_serialize_var_name = infos["serialize_var_name"] if "serialize_var_name" in infos else "output"
        info_serialize_value_var_type = infos["serialize_value_var_type"] if "serialize_value_var_type" in infos else "value"

        clazz = {
            "fields": [],
            "serialize": [],
            "deserialize": [],
            "var_list": [],
            "docs": [],
            "other_imports": [],
            "extra_classes": []
        }

        fields = array_type[1]

        extra_fields = []
        extra_serialize = []
        extra_deserialize = []
        extra_var_list = []
        extra_docs = []
        extra_class_name = self.class_name + (camel_case(field_name)[0].upper() + camel_case(field_name)[1:])

        field_name_var_name = camel_case(field_name)

        for field in fields:
            field_name = field["name"]
            field_type = field["type"]
            round_str = f"{round}" if round > 0 else ""
            return_value = self.generate_field(field_name, field_type, {
                "deserialize_var_name": "arrayInput" + round_str,
                "serialize_var_name": "arrayOutput" + round_str,
                "serialize_value_var_type": "arrayValue" + round_str
            }, round + 1)

            if return_value is not None:
                extra_fields += return_value["fields"]
                extra_serialize += return_value["serialize"]
                extra_deserialize += return_value["deserialize"]
                extra_var_list += return_value["var_list"]
                extra_docs += return_value["docs"]
                clazz["other_imports"] += return_value["other_imports"]
                clazz["extra_classes"] += return_value["extra_classes"]

        clazz["fields"] += [f"val {field_name_var_name}: List<{extra_class_name}>, // varint array"]
        clazz["extra_classes"] += [self.generate_extra_class(extra_class_name, extra_fields, extra_docs)]
        clazz["serialize"] += [self.generate_extra_class_serializer(field_name_var_name, info_serialize_var_name, info_serialize_value_var_type, extra_serialize, round)]
        clazz["deserialize"] += [self.generate_extra_class_deserializer(field_name_var_name, info_deserialize_var_name, extra_deserialize, extra_var_list, extra_class_name, round)]
        clazz["docs"] += [f" * @param {field_name_var_name} list of {extra_class_name}"]
        clazz["var_list"] += [field_name_var_name]

        return clazz

    def generate_array(self, field_name: str, array: dict, infos: dict, round: int = 0) -> dict:
        clazz = {
            "fields": [],
            "serialize": [],
            "deserialize": [],
            "var_list": [],
            "docs": [],
            "other_imports": [],
            "extra_classes": []
        }

        if "countType" not in array:
            return clazz

        count_type = array["countType"]
        array_type = array["type"]
        array_type_type = type(array_type)

        if count_type == "varint":
            if array_type_type is str:
                return_value = self.generate_basic_type_array(field_name, array_type, infos)
                self.add_to_clazz_field(return_value, clazz)
            elif array_type_type is list:
                array_type = array_type[0]
                if array_type == "container":
                    return_value = self.generate_container_array(field_name, array, infos, round)
                    self.add_to_clazz_field(return_value, clazz)
                elif array_type == "array":
                    array_fields = array["type"][1]
                    return_value = self.generate_array(field_name, array_fields, infos, round)
                    for field in return_value["fields"]:
                        field_split = field.split(" ")
                        clazz["fields"] += [f"val {field_split[1]} List<" + field_split[2].split(",")[0] + f">, // varint array"]
                    for serialize in return_value["serialize"]:
                        serialize_split = serialize.split(" ")
                        round_str = f"{(round + 1)}" if (round + 1) > 0 else ""
                        serialize_split_rest = " ".join(serialize_split[1:])
                        clazz["serialize"] += [serialize_split[0] + " { " + f"arrayValue{round_str}, arrayOutput{round_str} -> arrayOutput{round_str}.writeVarIntArray(arrayValue{round_str}) " + serialize_split_rest + " }"]
                    for deserialize in return_value["deserialize"]:
                        deserialize_split = deserialize.split(" { ")
                        round_str = f"{(round + 1)}" if (round + 1) > 0 else ""
                        clazz["deserialize"] += [deserialize_split[0] + " { " + f"arrayInput{round_str} -> arrayInput{round_str}.readVarIntArray {{ " + deserialize_split[1] + " }"]
                    clazz["var_list"] += return_value["var_list"]
                    clazz["docs"] += return_value["docs"]
                    clazz["other_imports"] += return_value["other_imports"]
                    clazz["extra_classes"] += return_value["extra_classes"]
                else:
                    raise Exception("Unknown array type")
        else:
            raise Exception("Not supported")

        return clazz

    def generate_container(self, field_name: str, container: dict, infos: dict) -> dict:
        clazz = {
            "fields": [],
            "serialize": [],
            "deserialize": [],
            "var_list": [],
            "docs": [],
            "other_imports": [],
            "extra_classes": []
        }

        for field in container:
            field_name = field["name"]
            field_type = field["type"]
            return_value = self.generate_field(field_name, field_type, infos)

            if return_value is not None:
                self.add_to_clazz_field(return_value, clazz)

        return clazz

    def generate_option_container(self, parent_field_name: str, container: list, infos: dict) -> dict:
        parent_field_name_var_name = camel_case(parent_field_name)
        parent_field_boolean_var_name = ("has" + parent_field_name_var_name[0].upper() + parent_field_name_var_name[1:])

        clazz = {
            "fields": [],
            "serialize": [],
            "deserialize": [],
            "var_list": [],
            "docs": [],
            "other_imports": [],
            "extra_classes": []
        }

        info_deserialize_var_name = infos["deserialize_var_name"] if "deserialize_var_name" in infos else "input"
        info_serialize_var_name = infos["serialize_var_name"] if "serialize_var_name" in infos else "output"
        info_serialize_value_var_type = infos["serialize_value_var_type"] if "serialize_value_var_type" in infos else "value"

        clazz["fields"] += [f"val {parent_field_boolean_var_name}: Boolean,"]
        clazz["deserialize"] += [f"val {parent_field_boolean_var_name} = {info_deserialize_var_name}.readBoolean()"]
        clazz["serialize"] += [f"{info_serialize_var_name}.writeBoolean({info_serialize_value_var_type}.{parent_field_boolean_var_name})"]
        clazz["var_list"] += [parent_field_boolean_var_name]
        clazz["docs"] += [f" * @param {parent_field_boolean_var_name} {parent_field_name} is present"]

        for field in container:
            field_name = field['name']
            field_type = field['type']

            if field_type == ['buffer', {'countType': 'varint'}]:
                field_type = "buffer"

            field_type_type = type(field_type)

            if field_type_type is str:
                return_value = self.generate_option_basic_type_custom(field_name, field_type, parent_field_boolean_var_name, infos)
                self.add_to_clazz_field(return_value, clazz)
            elif field_type_type is list:
                field_sub_type_name = field_type[0]
                if field_sub_type_name == "array":
                    print("Not supported yet")
                elif field_sub_type_name == "switch":
                    return_value = self.generate_switch(field_name, field_type, infos)
                    self.add_to_clazz_field(return_value, clazz)
                    pass
                elif field_sub_type_name == "bitfield":
                    print("Not supported yet")
                else:
                    raise Exception(f"Not supported yet: {field_sub_type_name}")
            else:
                raise Exception(f"Not supported yet: {field_type_type}")

        return clazz

    def generate_switch(self, field_name: str, switch: dict, infos: dict) -> dict:
        if type(switch) is list:
            switch = switch[1]

        clazz = {
            "fields": [],
            "serialize": [],
            "deserialize": [],
            "var_list": [],
            "docs": [],
            "other_imports": [],
            "extra_classes": []
        }

        info_deserialize_var_name = infos["deserialize_var_name"] if "deserialize_var_name" in infos else "input"
        info_serialize_var_name = infos["serialize_var_name"] if "serialize_var_name" in infos else "output"
        info_serialize_value_var_type = infos["serialize_value_var_type"] if "serialize_value_var_type" in infos else "value"

        compare_to_field = switch["compareTo"].replace("../", "")
        if "/" in compare_to_field:
            return clazz

        compare_to_field_type = ""
        compare_to_field_var_name = camel_case(compare_to_field)
        compare_to_field_var_name_with_value = f"{info_serialize_value_var_type}.{compare_to_field_var_name}"
        if "../" in switch["compareTo"]:
            compare_to_field_var_name_with_value = "value." + compare_to_field_var_name_with_value.split(".")[1]

        default = switch["default"] if "default" in switch else None
        fields = switch["fields"]
        kotlin_type_fix = {}
        field_var_name = camel_case(field_name)

        deserialize = []
        serialize = []

        other_switch = False
        other_switch_value = []
        other_switch_value_dict = []

        # filter self.class_fields for compare_to_field_var_name
        for field in self.internal_list:
            if compare_to_field in field:
                compare_to_field_type = field.split(":")[1].split(",")[0].strip()
                break

        if "Byte" in compare_to_field_type or "UByte" in compare_to_field_type:
            compare_to_field_var_name = compare_to_field_var_name + ".toInt()"
            compare_to_field_var_name_with_value = compare_to_field_var_name_with_value + ".toInt()"

        for field in fields:
            field_value = fields[field]

            if "String" in compare_to_field_type:
                field = f'"{field}"'
                field = field.replace('""', '"')

            if field_value == ['buffer', {'countType': 'varint'}]:
                field_value = "buffer"

            if field_value == ['option', ['buffer', {'countType': 'varint'}]]:
                field_value = ['option', 'buffer']

            field_type_type = type(field_value)

            if field_type_type is str:
                field_var_name = camel_case(field_name)
                kotlin_type = kotlin_types_wrapper[field_value]

                if kotlin_type == "native":
                    print(f"Unknown type: {field_value}")
                    continue

                if field_value == "void":
                    deserialize += [f"{field} -> null"]
                else:
                    kotlin_type_fix = kotlin_type
                    deserialize += [f"{field} -> {info_deserialize_var_name}.{kotlin_type['deserialize']}"]
                    serialize += [f"{field} -> {info_serialize_var_name}.{kotlin_type['serialize'] % (info_serialize_value_var_type + '.' + field_var_name + '!!')}"]
            else:
                # Option?
                if field_value[0] == "option" and type(field_value[1]) is str:
                    kotlin_type = kotlin_types_wrapper[field_value[1]]
                    kotlin_type_fix = kotlin_type
                    boolean_field_var_name = ("has" + field_var_name[0].upper() + field_var_name[1:])

                    # Get last field
                    if len(clazz["fields"]) >= 1 and boolean_field_var_name not in clazz["fields"][-1] or len(clazz["fields"]) == 0:
                        build_other_switch = {
                            "compareTo": "../" + compare_to_field,
                            "fields": {x: "bool" for x in fields.keys()},
                        }

                        return_value = self.generate_switch(boolean_field_var_name, build_other_switch, infos)
                        self.add_to_clazz_field(return_value, clazz)

                    deserialize += [f"{field} -> if ({boolean_field_var_name}!!) {info_deserialize_var_name}.{kotlin_type['deserialize']} else null"]
                    serialize += [f"{field} -> if ({info_serialize_value_var_type}.{boolean_field_var_name}!!) {info_serialize_var_name}.{kotlin_type['serialize'] % (info_serialize_value_var_type + '.' + field_var_name + '!!')}"]
                    continue

                # Array?
                if field_value[0] == "array":
                    return_value = self.generate_array(field_name, field_value[1], infos, 1)
                    if len(return_value["fields"]) > 0:
                        fields_build = return_value["fields"][0].split(", // ", 1)
                        fields_build_second = f" // {fields_build[1]}" if len(return_value["fields"]) > 1 else ""
                        fields_build = fields_build[0] + f"?,{fields_build_second}"

                        if fields_build not in clazz["fields"]:
                            clazz["fields"] += [fields_build]
                            clazz["other_imports"] += return_value["other_imports"]
                            clazz["extra_classes"] += return_value["extra_classes"]
                            clazz["var_list"] += return_value["var_list"]
                            clazz["docs"] += return_value["docs"]

                        deserialize_str = return_value['deserialize'][0].split(" = ", 1)[1]
                        serialize_build = return_value['serialize'][0].split(") {", 1)
                        serialize_str = serialize_build[0] + "!!) {" + serialize_build[1]

                        deserialize += [f"{field} -> {deserialize_str}"]
                        serialize += [f"{field} -> {serialize_str}"]
                    continue

                other_switch = True
                other_switch_value = field_value
                if field_value[0] == "container":
                    for field_container in field_value[1]:
                        inside = False
                        for it in other_switch_value_dict:
                            if it["value"] == field_container:
                                inside = True
                                break

                        if inside is False:
                            other_switch_value_dict += [{"value": field_container, "keys": [field]}]
                        else:
                            for x in other_switch_value_dict:
                                if x["value"] == field_container:
                                    x["keys"] += [field]
                elif field_value[0] == "option":
                    for field_container in field_value[1][1]:
                        inside = False
                        for it in other_switch_value_dict:
                            if it["value"] == field_container:
                                inside = True
                                break

                        if inside is False:
                            other_switch_value_dict += [{"value": field_container, "keys": [field]}]
                        else:
                            for x in other_switch_value_dict:
                                if x["value"] == field_container:
                                    x["keys"] += [field]
                else:
                    raise Exception(field_value)

        if other_switch:
            other_switch_value_type = other_switch_value[0]

            if other_switch_value_type == "container":
                for field in other_switch_value_dict:
                    field_info = field['value']
                    field_keys = field['keys']
                    field_name = field_info['name']
                    field_type = field_info['type']

                    build_other_switch = {
                        "compareTo": compare_to_field,
                        "fields": {x: field_type for x in field_keys}
                    }

                    return_value = self.generate_switch(field_name, build_other_switch, infos)
                    self.add_to_clazz_field(return_value, clazz)
            elif other_switch_value_type == "option":
                other_switch_value_type = other_switch_value[1][0]
                if other_switch_value_type == "container":
                    for field in other_switch_value_dict:
                        field_info = field['value']
                        field_keys = field['keys']

                        if type(field_info) is list:
                            for field_sub in field_info:
                                field_name = field_sub['name']
                                field_type = [
                                    "option",
                                    field_sub['type']
                                ]

                                build_other_switch = {
                                    "compareTo": switch["compareTo"],
                                    "fields": {x: field_type for x in field_keys}
                                }

                                return_value = self.generate_switch(field_name, build_other_switch, infos)
                                self.add_to_clazz_field(return_value, clazz)

                        else:
                            field_name = field_info['name']
                            field_type = [
                                "option",
                                field_info['type']
                            ]

                            build_other_switch = {
                                "compareTo": switch["compareTo"],
                                "fields": {x: field_type for x in field_keys}
                            }

                            return_value = self.generate_switch(field_name, build_other_switch, infos)
                            self.add_to_clazz_field(return_value, clazz)
            else:
                raise Exception("Not supported")
            return clazz

        if default is not None:
            if default == ['buffer', {'countType': 'varint'}]:
                default = "buffer"

            default_kotlin_type = kotlin_types_wrapper[default]
            if default == "void":
                deserialize += ["else -> null"]
                serialize += ["else -> {}"]
            else:
                kotlin_type_fix = default_kotlin_type
                deserialize += [f"else -> {info_deserialize_var_name}.{default_kotlin_type['deserialize']}"]
                serialize += [f"else -> {info_serialize_var_name}.{default_kotlin_type['serialize'] % (info_serialize_value_var_type + '.' + field_var_name + '!!')}"]

        else:
            if compare_to_field_type == "Boolean" and len(deserialize) == 2:
                print("Boolean switch with no default")
            else:
                deserialize += ["else -> null"]
                serialize += ["else -> {}"]

        deserialize_str = "\n                ".join(deserialize)
        serialize_str = "\n                ".join(serialize)

        deserialize_str = f"""val {field_var_name} = when ({compare_to_field_var_name}) {{
                            {deserialize_str}
                        }}"""

        serialize_str = f"""when ({compare_to_field_var_name_with_value}) {{
                            {serialize_str}
                        }}"""

        clazz["deserialize"] += [deserialize_str]
        clazz["serialize"] += [serialize_str]

        if kotlin_type_fix == {}:
            return clazz

        kotlin_type_str = kotlin_type_fix["type"]
        field_comment = (" // " + kotlin_type_fix["comment"]) if "comment" in kotlin_type_fix else ""

        clazz["fields"] += [f"val {field_var_name}: {kotlin_type_str}?,{field_comment}"]

        clazz["var_list"] += [field_var_name]

        clazz["docs"] += [f" * @param {field_var_name} {field_name}"]

        if "import" in kotlin_type_fix:
            clazz["other_imports"] += [kotlin_type_fix["import"]]

        return clazz

    def generate_field(self, field_name: str, field, infos: dict, round: int = 0) -> dict:
        field_type = type(field)

        clazz = {
            "fields": [],
            "serialize": [],
            "deserialize": [],
            "var_list": [],
            "docs": [],
            "other_imports": [],
            "extra_classes": []
        }

        return_value = None

        if field_type is list:
            field_type = field[0]
            field = field[1]
            if field_type == "array":
                return_value = self.generate_array(field_name, field, infos, round)
            elif field_type == "container":
                return_value = self.generate_container(field_name, field, infos)
            elif field_type == "option":
                fields_type = type(field)
                if fields_type is str:
                    return_value = self.generate_option_basic_type(field_name, field, infos)
                elif fields_type is list:
                    field_type = field[0]
                    field = field[1]
                    if field_type == "array":
                        print("Option array not supported yet")
                    elif field_type == "container":
                        return_value = self.generate_option_container(field_name, field, infos)
                    else:
                        raise Exception("Unknown field type: " + field_type)
                else:
                    raise Exception("Unknown option type")
            elif field_type == "buffer":
                buffer_count_type = field["countType"]
                if buffer_count_type == "varint":
                    return_value = self.generate_basic_type(field_name, "buffer", infos)
                else:
                    raise Exception("Not supported")
            elif field_type == "switch":
                return_value = self.generate_switch(field_name, field, infos)
            elif field_type == "particleData":
                pass
            elif field_type == "bitfield":
                return_value = self.generate_bitfield(field_name, field, infos)
            else:
                print("##" + str(field))
                # raise Exception("Unknown fields type: " + field_type)

        elif field_type is str:
            return_value = self.generate_basic_type(field_name, field, infos)
        else:
            raise Exception("Invalid fields type")

        self.add_to_clazz_field(return_value, clazz)

        return clazz
