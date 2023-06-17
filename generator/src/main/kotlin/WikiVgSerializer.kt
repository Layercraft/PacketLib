import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.regex.Pattern

class WikiVgSerializer(
    protocolVersion: ProtocolVersion
) {

    private val wikiVgData: String = runBlocking {
        Downloader.getWikiVgPage(protocolVersion)
    }


    fun get(packetId: String, state: String, direction: String): WikiVgData {
        val bodyTextPattern = Pattern.compile("<body.*?>(.*?)</body>", Pattern.DOTALL)
        val bodyTextMatcher = bodyTextPattern.matcher(wikiVgData)
        bodyTextMatcher.find()
        val bodyText = bodyTextMatcher.group(1)

        val directionConverted = if (direction == "clientbound") "Client" else "Server"
        val stateCapitalized = state.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        val packetIdFormatted = "0x" + packetId.split("x")[1].uppercase(Locale.getDefault())
        val regex = "<td.*?>$packetIdFormatted\\n</td>\\n<td.*?>$stateCapitalized\\n</td>\\n<td.*?>$directionConverted\\n</td>"
        val result = bodyText.split(regex.toRegex(), 0)[0].split("<h4>").last()

        val idRegex = "<span class=\"mw-headline\" id=\"(.*?)\">"
        val id = Pattern.compile(idRegex).matcher(result).group(1)

        val nameRegex = "<span class=\"mw-headline\" id=\".*?\">"
        val name = result.split(nameRegex.toRegex(), 0)[1].split("</span>")[0]

        return WikiVgData(id, name)
    }

}

data class WikiVgData(val id: String, val name: String)