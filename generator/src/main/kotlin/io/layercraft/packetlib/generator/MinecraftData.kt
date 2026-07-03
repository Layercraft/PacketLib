package io.layercraft.packetlib.generator

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.file.Files
import java.nio.file.Path

/**
 * Downloads (and caches on disk) protocol data from the PrismarineJS/minecraft-data repository.
 */
class MinecraftData(private val cacheDir: Path) {

    private val client: HttpClient = HttpClient.newHttpClient()

    val dataPaths: JsonObject by lazy { fetchJson("dataPaths.json").jsonObject }

    val protocolVersions: JsonArray by lazy { fetchJson("pc/common/protocolVersions.json") as JsonArray }

    /** @param folder a data folder like `pc/1.19.3` */
    fun protocol(folder: String): JsonObject = fetchJson("$folder/protocol.json").jsonObject

    /** Protocol numbers by minecraft version, e.g. `1.19.3` -> 761. */
    fun protocolNumbers(): Map<String, Int> = protocolVersions.associate {
        it.jsonObject["minecraftVersion"]!!.jsonPrimitive.content to it.jsonObject["version"]!!.jsonPrimitive.int
    }

    /** Protocol number from a data folder's version.json, e.g. `pc/1.7` -> 5. */
    fun folderProtocolNumber(folder: String): Int =
        fetchJson("$folder/version.json").jsonObject["version"]!!.jsonPrimitive.int

    private fun fetchJson(path: String): kotlinx.serialization.json.JsonElement {
        val cacheFile = cacheDir.resolve(path.replace('/', '_'))
        val text = if (Files.exists(cacheFile)) {
            Files.readString(cacheFile)
        } else {
            val url = "$BASE_URL/$path"
            println("Downloading $url")
            val request = HttpRequest.newBuilder(URI.create(url)).GET().build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString())
            check(response.statusCode() == 200) { "GET $url failed: HTTP ${response.statusCode()}" }
            Files.createDirectories(cacheDir)
            Files.writeString(cacheFile, response.body())
            response.body()
        }
        return Json.parseToJsonElement(text)
    }

    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/PrismarineJS/minecraft-data/master/data"

        fun sourceUrl(folder: String): String =
            "https://github.com/PrismarineJS/minecraft-data/blob/master/data/$folder/protocol.json"
    }
}
