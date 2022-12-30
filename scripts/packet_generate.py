import re
import os
import requests

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
    "array": "native",
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
        "serialize": "writeNbt(%s)"
    },
    "optionalNbt": {
        "type": "NBT",
        "deserialize": "readNbt()",
        "serialize": "writeNbt(%s)"
    },
    "position": {
        "type": "Position",
        "deserialize": "readPosition()",
        "serialize": "writePosition(%s)",
        "import": "import io.layercraft.packetlib.types.Position"
    },
    "entityMetadataLoop": "native",
    "topBitSetTerminatedArray": "native",
    "bitfield": "native",
    "slot": "native",
    "entityMetadata": "native",
    "previousMessages": "native",
    "command_node": "native",
    "chunkBlockEntity": "native",
}

src = "src/main/kotlin"

wikivg_url = "https://wiki.vg/index.php?title=Protocol"
wikivg_url_end = "&oldid=17873"
wikivg_text = requests.get(wikivg_url + wikivg_url_end).text

data_url = "https://raw.githubusercontent.com/PrismarineJS/minecraft-data/master/data/pc/1.19.2/protocol.json"

version = data_url.split("/")[-2]
version_underline = version.replace(".", "_")

minecraft_codec = []

runs = []


def camel_case(s):
    camel_case_str = re.sub(r"([-_])([a-zA-Z])",
                            lambda m: m.group(2).upper(), s)
    camel_case_str = camel_case_str[0].lower() + camel_case_str[1:]
    return camel_case_str


def codec_generate():
    handshaking_text = ""
    login_text = ""
    status_text = ""
    play_text = ""

    for packet in minecraft_codec:
        class_name = packet["class"]
        package_string = packet["package"]
        status = packet["status"]
        id = packet["id"]

        direction_string = "registerClientBoundPacket" if packet[
                                                              "direction"] == "clientbound" else "registerServerBoundPacket"
        add_text = f"""                    .{direction_string}({id}, {package_string}.{class_name}::class, {package_string}.{class_name}) \n"""

        if status == "handshaking":
            handshaking_text += add_text
        elif status == "login":
            login_text += add_text
        elif status == "status":
            status_text += add_text
        elif status == "play":
            play_text += add_text

    text = f"""
    val V{version_underline}: MinecraftCodec =
        MinecraftCodec.create(ProtocolVersion.V{version_underline})
            .registerPacketRegistry(
                PacketState.HANDSHAKING,
                MinecraftCodecRegistry.create()
{handshaking_text})
            .registerPacketRegistry(
                PacketState.LOGIN,
                MinecraftCodecRegistry.create()
{login_text})
            .registerPacketRegistry(
                PacketState.STATUS,
                MinecraftCodecRegistry.create()
{status_text})
            .registerPacketRegistry(
                PacketState.PLAY,
                MinecraftCodecRegistry.create()
{play_text})
    """

    # write to codec.kt.tmp file
    with open(f"codec.kt.tmp", "w+") as f:
        f.write(text)


def transfer_packets(direction: str, status: str, data: dict) -> list:
    packets_result = []

    # Packet = data with "packet_" key
    packets = {}
    for packet in data:
        if packet.startswith("packet_"):
            packets[packet] = data[packet]

    packet_info = data["packet"][1]
    packet_ids = []
    for ids in packet_info:
        if ids["name"] == "name":
            packet_ids = ids["type"][1]["mappings"]
            break

    packet_names = []
    for names in packet_info:
        if names["name"] == "params":
            packet_names = names["type"][1]["fields"]
            break

    # clear packet_info
    packet_info = []

    for id in packet_ids:
        name = packet_ids[id]
        packet_name = packet_names[name]

        packet_info.append({
            "id": id,
            "name": name,
            "packet_name": packet_name
        })

    for packet in packet_info:
        id = packet["id"]
        name = packet["name"]
        packet_name = packet["packet_name"]

        packet_fields = packets[packet_name][1]

        packets_result.append({
            "id": id,
            "name": name,
            "fields": packet_fields,
            "direction": direction,
            "status": status
        })

    return packets_result


def packets(data: dict) -> list:
    print("Generating packets...")
    packets_data = []

    for status in data:
        if status == "types":
            continue

        to_client = data[status]["toClient"]["types"]
        to_server = data[status]["toServer"]["types"]

        client_bound = transfer_packets("clientbound", status, to_client)
        server_bound = transfer_packets("serverbound", status, to_server)

        packets_data += client_bound + server_bound

    return packets_data


def add_run(source: str = "None"):
    global runs
    runs += [source]


def wikivg_data(packet_id: str, state: str, direction: str) -> dict:
    body_text = re.search('<body.*?>(.*?)</body>',
                          wikivg_text, re.DOTALL).group(1)

    direction = "Client" if direction == "clientbound" else "Server"
    state = state.capitalize()
    regex = rf'<td.*?>{packet_id}\n</td>\n<td.*?>{state}\n</td>\n<td.*?>{direction}\n</td>'
    result = re.split(regex, body_text, re.DOTALL)[0]
    result = result.split("<h4>")[-1]
    id = result.split('<span class="mw-headline" id="')[1].split('">')[0]
    name = result.split('">')[1].split("</span>")[0]

    return {
        "id": id,
        "name": name
    }


class PacketGenerator:
    def __init__(self, packet_dict: dict):
        self.packet = packet_dict
        self.id = packet_dict['id']
        self.name = packet_dict['name']
        self.direction = packet_dict['direction']
        self.status = packet_dict["status"]
        self.fields = packet_dict['fields']
        self.version = version
        self.version_underline = version.replace(".", "_")
        self.package = f"io.layercraft.packetlib.packets.v{self.version_underline}.{self.status}.{self.direction}"
        self.package_path = f"io/layercraft/packetlib/packets/v{self.version_underline}/{self.status}/{self.direction}"
        self.class_name = self.name.replace("_", " ").title().replace(" ", "") + "Packet"
        wiki_data = wikivg_data(self.id, self.status, self.direction)
        self.wikivg_id = wiki_data['id']
        self.wikivg_name = wiki_data['name']

        self.class_fields = []
        self.class_serialize = []
        self.class_deserialize = []
        self.class_var_list = []
        self.class_docs = []
        self.class_other_imports = []
        self.additional_class = ""

    def direction_interface(self) -> str:
        if self.direction == "serverbound":
            return "ServerBoundPacket"
        elif self.direction == "clientbound":
            return "ClientBoundPacket"

    def generate_basic_type(self, field_name: str, field_type: str, field_var_name: str):
        kotlin_type = kotlin_types_wrapper[field_type]
        field_comment = " // " + \
                        kotlin_type["comment"] if "comment" in kotlin_type else ""

        if kotlin_type == "native":
            add_run("Unsupported type: " + field_type)
            print("Not supported yet")
            return
        elif kotlin_type == "":
            raise Exception("Not supported")

        kotlin_type_str = kotlin_type["type"]
        kotlin_type_serialize = kotlin_type["serialize"]
        kotlin_type_deserialize = kotlin_type["deserialize"]

        self.class_fields += [f"val {field_var_name}: {kotlin_type_str},{field_comment}"]
        self.class_deserialize += [f"val {field_var_name} = input.{kotlin_type_deserialize}"]

        self.class_serialize += [f"output.{kotlin_type_serialize % ('value.' + field_var_name)}"]

        self.class_var_list += [field_var_name]
        self.class_docs += [f" * @property {field_var_name} {field_name}"]

        if "import" in kotlin_type:
            self.class_other_imports += [kotlin_type["import"]]

    def generate_option_basic_type(self, field_name: str, field_type: str, field_var_name: str):
        kotlin_type = kotlin_types_wrapper[field_type]

        field_comment = (" // " + kotlin_type["comment"]) if "comment" in kotlin_type else ""

        boolean_field_var_name = (
                "has" + field_var_name[0].upper() + field_var_name[1:])

        kotlin_type_str = kotlin_type["type"]
        kotlin_type_serialize = kotlin_type["serialize"]
        kotlin_type_deserialize = kotlin_type["deserialize"]

        self.class_fields += [f"val {boolean_field_var_name}: Boolean,"]
        self.class_fields += [f"val {field_var_name}: {kotlin_type_str}?,{field_comment}"]

        self.class_deserialize += [f"val {boolean_field_var_name} = input.readBoolean()"]
        self.class_deserialize += [
            f"val {field_var_name} = if ({boolean_field_var_name}) input.{kotlin_type_deserialize} else null"]

        self.class_serialize += [f"output.writeBoolean(value.{boolean_field_var_name})"]
        self.class_serialize += [
            f"if (value.{boolean_field_var_name}) output.{kotlin_type_serialize % ('value.' + field_var_name + '!!')}"]

        self.class_var_list += [boolean_field_var_name,
                                field_var_name]

        self.class_docs += [f" * @property {boolean_field_var_name} {field_name} is present"]
        self.class_docs += [f" * @property {field_var_name} {field_name}"]

        if "import" in kotlin_type:
            self.class_other_imports += [kotlin_type["import"]]

    def generate_option_basic_type_custom(self, field_name: str, field_type: str, field_var_name: str, boolean_field_var_name: str):
        kotlin_type = kotlin_types_wrapper[field_type]

        field_comment = (" // " + kotlin_type["comment"]) if "comment" in kotlin_type else ""

        kotlin_type_str = kotlin_type["type"]
        kotlin_type_serialize = kotlin_type["serialize"]
        kotlin_type_deserialize = kotlin_type["deserialize"]

        self.class_fields += [f"val {field_var_name}: {kotlin_type_str}?,{field_comment}"]

        self.class_deserialize += [f"val {field_var_name} = if ({boolean_field_var_name}) input.{kotlin_type_deserialize} else null"]

        self.class_serialize += [f"if (value.{boolean_field_var_name}) output.{kotlin_type_serialize % ('value.' + field_var_name + '!!')}"]

        self.class_var_list += [field_var_name]

        self.class_docs += [f" * @property {field_var_name} {field_name}"]

        if "import" in kotlin_type:
            self.class_other_imports += [kotlin_type["import"]]

    def generate_basic_type_array(self, field_name: str, array_type: str, field_var_name: str):
        array_kotlin_type = kotlin_types_wrapper[array_type]

        if array_kotlin_type == "native":
            add_run("Unsupported type: " + array_type)
            print("Not supported yet")
            return

        kotlin_type_str = array_kotlin_type["type"]
        kotlin_type_serialize = array_kotlin_type["serialize"]
        kotlin_type_deserialize = array_kotlin_type["deserialize"]

        self.class_fields += [f"val {field_var_name}: List<{kotlin_type_str}>, // varint array"]
        self.class_deserialize += [f"val {field_var_name} = input.readVarIntArray {{ arrayInput -> arrayInput.{kotlin_type_deserialize} }}"]
        self.class_serialize += [f"output.writeVarIntArray(value.{field_var_name}) {{ arrayValue, arrayOutput -> arrayOutput.{kotlin_type_serialize % 'arrayValue'}}}"]
        self.class_var_list += [field_var_name]
        self.class_docs += [f" * @property {field_var_name} {field_name}"]

        if "import" in array_kotlin_type:
            self.class_other_imports += [array_kotlin_type["import"]]

    def generate_array(self, field_name: str, array: dict, field_var_name: str):
        count_type = array["countType"]
        array_type = array["type"]
        array_type_type = type(array_type)

        if count_type == "varint":
            if array_type_type is str:
                self.generate_basic_type_array(field_name, array_type, field_var_name)
            elif array_type_type is dict:
                add_run("CONTAINER ARRAY")
                print("Not supported yet")
        else:
            raise Exception("Not supported")

    def generate_option_container(self, container: list, parent_field_name: str, parent_field_var_name: str):
        parent_field_boolean_var_name = ("has" + parent_field_var_name[0].upper() + parent_field_var_name[1:])

        self.class_fields += [f"val {parent_field_boolean_var_name}: Boolean,"]
        self.class_deserialize += [f"val {parent_field_boolean_var_name} = input.readBoolean()"]
        self.class_serialize += [f"output.writeBoolean(value.{parent_field_boolean_var_name})"]
        self.class_var_list += [parent_field_boolean_var_name]
        self.class_docs += [f" * @property {parent_field_boolean_var_name} {parent_field_name} is present"]

        for field in container:
            field_name = field['name']
            field_type = field['type']
            field_var_name = camel_case(field_name)
            field_type_type = type(field_type)

            if field_type_type is str:
                self.generate_option_basic_type_custom(field_name, field_type, field_var_name, parent_field_boolean_var_name)
            elif field_type_type is list:
                field_sub_type_name = field_type[0]
                if field_sub_type_name == "buffer":
                    buffer_count_type = field_type[1]["countType"]
                    if buffer_count_type == "varint":
                        self.generate_option_basic_type_custom(field_name, "buffer", field_var_name, parent_field_boolean_var_name)
                    else:
                        raise Exception("Not supported")
                elif field_sub_type_name == "array":
                    add_run("ARRAY IN OPT CONTAINER")
                    print("Not supported yet")
                else:
                    raise Exception("Not supported")
            else:
                raise Exception("Not supported")

    def generate_switch(self, field_name: str, field_var_name: str, switch: dict):
        compare_to_field = switch["compareTo"]
        compare_to_field_type = ""
        compare_to_field_var_name = camel_case(compare_to_field)
        default = switch["default"] if "default" in switch else None
        fields = switch["fields"]
        kotlin_type_fix = {}

        # filter self.class_fields for compare_to_field_var_name
        for field in self.class_fields:
            if compare_to_field in field:
                compare_to_field_type = field.split(":")[1].split(",")[0].strip()
                break

        if "Byte" in compare_to_field_type:
            compare_to_field_var_name = compare_to_field_var_name + ".toInt()"

        deserialize = []
        serialize = []

        other_switch = False
        other_switch_value = []
        other_switch_value_dict = []

        for field in fields:
            field_value = fields[field]

            if field_value == ['buffer', {'countType': 'varint'}]:
                field_value = "buffer"

            field_type_type = type(field_value)

            if field_type_type is str:
                field_var_name = camel_case(field_name)
                kotlin_type = kotlin_types_wrapper[field_value]

                if field_value == "void":
                    deserialize += [f"{field} -> null"]
                else:
                    kotlin_type_fix = kotlin_type
                    deserialize += [f"{field} -> input.{kotlin_type['deserialize']}"]
                    serialize += [f"{field} -> output.{kotlin_type['serialize'] % ('value.' + field_var_name + '!!')}"]
            else:
                other_switch = True
                other_switch_value = field_value
                for field_container in field_value[1]:

                    inside = False
                    for it in other_switch_value_dict:
                        if it["value"] == field_container:
                            inside = True
                            break

                    if inside is False:
                        print("Adding field to dict")
                        other_switch_value_dict += [{"value": field_container, "keys": [field]}]
                    else:
                        for x in other_switch_value_dict:
                            if x["value"] == field_container:
                                x["keys"] += [field]

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

                    self.generate_switch(field_name, field_var_name, build_other_switch)
            elif other_switch_value_type == "array":
                add_run("ARRAY IN SWITCH")
                print("Not supported yet")
            else:
                raise Exception("Not supported")
            return

        if default is not None:
            if default == ['buffer', {'countType': 'varint'}]:
                default = "buffer"

            default_kotlin_type = kotlin_types_wrapper[default]
            if default == "void":
                deserialize += ["else -> null"]
                serialize += ["else -> {}"]
            else:
                kotlin_type_fix = default_kotlin_type
                deserialize += [f"else -> input.{default_kotlin_type['deserialize']}"]
                serialize += [f"else -> output.{default_kotlin_type['serialize'] % ('value.' + field_var_name + '!!')}"]

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

        serialize_str = f"""when (value.{compare_to_field_var_name}) {{
                {serialize_str}
            }}"""

        self.class_deserialize += [deserialize_str]
        self.class_serialize += [serialize_str]

        kotlin_type_str = kotlin_type_fix["type"]
        field_comment = (" // " + kotlin_type_fix["comment"]) if "comment" in kotlin_type_fix else ""

        self.class_fields += [f"val {field_var_name}: {kotlin_type_str}?,{field_comment}"]

        self.class_var_list += [field_var_name]

        self.class_docs += [f" * @property {field_var_name} {field_name}"]

        if "import" in kotlin_type_fix:
            self.class_other_imports += [kotlin_type_fix["import"]]

    def generate_container(self, container: list):
        for field in container:
            field_name = field['name']
            field_type = field['type']
            field_var_name = camel_case(field_name)
            field_type_type = type(field_type)

            if field_type_type is str:
                self.generate_basic_type(field_name, field_type, field_var_name)
            elif field_type_type is list:
                field_sub_type_name = field_type[0]
                if field_sub_type_name == "option":
                    field_sub_type_name = field_type[1]
                    field_sub_type_name_type = type(field_sub_type_name)

                    if field_sub_type_name_type is str:
                        # Option basic type
                        self.generate_option_basic_type(field_name, field_sub_type_name, field_var_name)
                    elif field_sub_type_name_type is list:
                        field_sub_type_name = field_sub_type_name[0]

                        if field_sub_type_name == "container":
                            # Option container
                            container = field_type[1][1]
                            self.generate_option_container(container, field_name, field_var_name)
                            continue
                        elif field_sub_type_name == "array":
                            # Option array
                            continue
                        else:
                            raise Exception("Not supported")
                    else:
                        raise Exception("Not supported")
                elif field_sub_type_name == "switch":
                    self.generate_switch(field_name, field_var_name, field_type[1])
                elif field_sub_type_name == "array":
                    # Array
                    self.generate_array(field_name, field_type[1], field_var_name)
                elif field_sub_type_name == "buffer":
                    buffer_count_type = field_type[1]["countType"]
                    if buffer_count_type == "varint":
                        self.generate_basic_type(field_name, "buffer", field_var_name)
                    else:
                        raise Exception("Not supported")
                else:
                    print("Not supported yet")
                    add_run("Unsupported type: " + field_sub_type_name)
            else:
                raise Exception("Not supported")

    def generate_fields_internal(self, fields: list):
        self.generate_container(fields)

    def generate_fields(self) -> dict:
        self.generate_fields_internal(self.fields)

        class_fields_str = "\n    ".join(self.class_fields)
        class_serialize_str = "\n            ".join(self.class_serialize)
        class_deserialize_str = "\n            ".join(self.class_deserialize)
        class_var_list_str = ", ".join(self.class_var_list)
        class_docs_str = "\n".join(self.class_docs)
        class_other_imports_str = "\n".join(self.class_other_imports)

        return {
            "class_fields_str": class_fields_str,
            "class_serialize_str": class_serialize_str,
            "class_deserialize_str": class_deserialize_str,
            "class_var_list_str": class_var_list_str,
            "class_documenation_str": class_docs_str,
            "class_other_imports_str": class_other_imports_str
        }

    def generate(self) -> str:

        print(f"Generate: {self.package}.{self.class_name} ({self.id})")

        fields = self.generate_fields()

        class_fields_str = fields["class_fields_str"]
        class_serialize_str = fields["class_serialize_str"]
        class_deserialize_str = fields["class_deserialize_str"]
        class_var_list_str = fields["class_var_list_str"]
        class_documenation_str = fields["class_documenation_str"]
        class_other_imports_str = fields["class_other_imports_str"]
        additional_class_str = self.additional_class

        wikivg_name = self.wikivg_name
        wikivg_id = self.wikivg_id

        id = self.id
        status = self.status
        direction = self.direction
        direction_interface = self.direction_interface()

        class_name = self.class_name

        class_str = f"""package {self.package}

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
{class_other_imports_str}
/**
 * {wikivg_name} | {id} | {status} | {direction}
 *
{class_documenation_str}
 * @see <a href="https://wiki.vg/index.php?title=Protocol{wikivg_url_end}#{wikivg_id}">https://wiki.vg/Protocol#{wikivg_id}</a>
 */

@MinecraftPacket(id = {id}, state = PacketState.{status.upper()}, direction = PacketDirection.{direction.upper()})
{"data" if len(class_fields_str) > 1 else ""} class {class_name}(
    {class_fields_str}
) : {direction_interface} {{
    companion object : PacketSerializer<{class_name}> {{
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): {class_name} {{
            {class_deserialize_str}

            return {class_name}({class_var_list_str})
        }}

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: {class_name}) {{
            {class_serialize_str}
        }}
    }}
}}
{additional_class_str}"""

        minecraft_codec.append(
            {
                "id": id,
                "direction": direction,
                "status": status,
                "class": class_name,
                "package": self.package
            }
        )

        print(f"Generated: {self.package}.{self.class_name} ({self.id})")

        return class_str

    def write(self):
        filename = f"{src}/{self.package_path}/{self.class_name}.kt"
        class_str = self.generate()

        os.makedirs(os.path.dirname(filename), exist_ok=True)
        with open(filename, "w+") as file:
            file.write(class_str)


# Main
if __name__ == "__main__":
    print("Starting...")
    general_data = requests.get(data_url).json()

    packet_data = packets(general_data)

    for packet in packet_data:
        generator = PacketGenerator(packet)
        generator.write()

    print("Done with Packet Generation!")

    print("Codec generation...")
    codec_generate()
    print("Done!")
    runs_grouped = {}
    for run in runs:
        runs_grouped[run] = runs_grouped.get(run, 0) + 1

    runs_grouped = {k: v for k, v in sorted(runs_grouped.items(), key=lambda item: item[1], reverse=True)}
    print(runs_grouped)

    print(len(runs))