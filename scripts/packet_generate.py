import re
import os
import requests
import packet_generate_text

versions = {
    "1.19.3": ("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.19.3/protocol.json", "17935"),
    "1.19.2": ("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.19.2/protocol.json", "17873"),
    "1.19.1": ("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.19.1/protocol.json", "17873"),
    "1.19": ("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.19/protocol.json", "17753"),
    "1.18.2": ("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.18.1/protocol.json", "17499"),
    "1.18.1": ("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.18.1/protocol.json", "17341"),
    "1.18": ("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.18/protocol.json", "17341"),
    "1.17.1": ("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.17.1/protocol.json", "16918"),
    "1.17": ("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.17/protocol.json", "16866"),
    "1.16.5": ("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.16.5/protocol.json", "16681"),
    "1.16.4": ("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.16.4/protocol.json", "16317"),
    "1.16.3": ("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.16.3/protocol.json", "16091"),
    "1.16.2": ("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.16.2/protocol.json", "16001"),
    "1.16.1": ("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.16.1/protocol.json", "15895"),
    "1.16": ("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.16/protocol.json", "15878"),
    "1.15.2": ("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.15.2/protocol.json", "16067"),
    "custom": ("", "")
}

src = "src/main/kotlin"
version = input("Version: ")
data_url = versions[version][0]
wikivg_url_end = "&oldid=" + versions[version][1]

wikivg_url = "https://wiki.vg/index.php?title=Protocol"
wikivg_text = requests.get(wikivg_url + wikivg_url_end).text

version_underline = version.replace(".", "_")

minecraft_codec = []


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


def wikivg_data(packet_id: str, state: str, direction: str) -> dict:
    body_text = re.search('<body.*?>(.*?)</body>',
                          wikivg_text, re.DOTALL).group(1)

    direction = "Client" if direction == "clientbound" else "Server"
    state = state.capitalize()
    packet_id = "0x" + packet_id.split("x")[1].upper()
    regex = rf'<td.*?>{packet_id}\n</td>\n<td.*?>{state}\n</td>\n<td.*?>{direction}\n</td>'
    result = re.split(regex, body_text, re.DOTALL)[0]
    result = result.split("<h4>")[-1]
    id = result.split('<span class="mw-headline" id="')[1].split('">')[0]
    name_regex = r'<span class="mw-headline" id=".*?">'
    name = re.split(name_regex, result, re.DOTALL)[1].split("</span>")[0]

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

    def generate_fields_internal(self, fields: list):
        # self.generate_container(fields)
        pgn = packet_generate_text.PacketGeneratorNew(self.packet)
        for field in fields:
            field_name = field['name']
            field_type = field['type']
            return_value = pgn.generate_field(field_name, field_type, {})
            self.class_fields += return_value["fields"]
            self.class_serialize += return_value["serialize"]
            self.class_deserialize += return_value["deserialize"]
            self.class_var_list += return_value["var_list"]
            self.class_docs += return_value["docs"]
            self.class_other_imports += return_value["other_imports"]
            for clazz in return_value["extra_classes"]:
                self.additional_class += "\n" + clazz

    def generate_fields(self) -> dict:
        self.generate_fields_internal(self.fields)

        class_fields_str = "\n    ".join(self.class_fields)
        class_serialize_str = "\n            ".join(self.class_serialize)
        class_deserialize_str = "\n            ".join(self.class_deserialize)
        class_var_list_str = ", ".join(self.class_var_list)
        class_docs_str = "\n".join(self.class_docs)
        class_other_imports_str = "\n".join(set(self.class_other_imports))

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

        try :
            fields = self.generate_fields()
        except Exception as e:
            print(f"Error: {self.package}.{self.class_name} ({self.id})")
            return "//TODO"

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
