
import io.ktor.client.HttpClient
import io.ktor.client.engine.java.Java
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

object Downloader {

    private val client = HttpClient(Java)

    suspend fun getWikiVgPage(protocolVersions: ProtocolVersion): String = client.get(protocolVersions.wikiVgUrl).bodyAsText()

    suspend fun getProtocolSpec(protocolVersions: ProtocolVersion): JsonObject = client.get(protocolVersions.url).bodyAsText().let { Json.parseToJsonElement(it).jsonObject }
}