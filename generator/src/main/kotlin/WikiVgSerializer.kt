import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.regex.Pattern

class WikiVgSerializer(
    private val protocolVersion: ProtocolVersion
) {

    private val wikiVgData: String = runBlocking {
        Downloader.getWikiVgPage(protocolVersion)
    }

    private val bodyText = run {
        val bodyTextPattern = Pattern.compile("<body.*?>(.*?)</body>", Pattern.DOTALL)
        val bodyTextMatcher = bodyTextPattern.matcher(wikiVgData)
        bodyTextMatcher.find()
        bodyTextMatcher.group(1)
    }




    fun get(packetId: String, state: PacketGeneratorState, direction: PacketGeneratorDirection): WikiVgData {
        val directionConverted = if (direction == PacketGeneratorDirection.CLIENT_BOUND) "Client" else "Server"
        val stateCapitalized = state.state.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        val packetIdFormatted = "0x" + packetId.split("x")[1].uppercase(Locale.getDefault())
        val regex = "<td.*?>$packetIdFormatted\\n</td>\\n<td.*?>$stateCapitalized\\n</td>\\n<td.*?>$directionConverted\\n</td>"
        val result = bodyText.split(regex.toRegex(), 0)[0].split("<h4>").last()

        val idRegex = "<span class=\"mw-headline\" id=\"(.*?)\">"
        val matcher = Pattern.compile(idRegex).matcher(result)
        matcher.find()
        val id = matcher.group(1)

        val nameRegex = "<span class=\"mw-headline\" id=\".*?\">"
        val name = result.split(nameRegex.toRegex(), 0)[1].split("</span>")[0]

        return WikiVgData(protocolVersion, id, name)
    }

}

data class WikiVgData(
    val protocolVersion: ProtocolVersion,
    val id: String,
    val name: String,
    val url : String = protocolVersion.wikiVgUrl + "#$id"
)