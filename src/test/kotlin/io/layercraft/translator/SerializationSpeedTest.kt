package io.layercraft.translator

import io.layercraft.translator.packets.login.serverbound.LoginPluginResponse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis
import kotlin.test.assertEquals

@Disabled
internal class SerializationSpeedTest {

    private val testObject = LoginPluginResponse(
        Int.MAX_VALUE,
        true,
        ByteArray(2) { 10 },
    )

    @Test
    fun `compare different serialization methods`() {
        val runs = 1000000

        //Run basic serialization
        var basicTime = 0.0
        for (i in 0..runs) {
            basicTime += onlyInputSerializer()
        }
        basicTime /= runs

        println("Basic serialization time: $basicTime milliseconds")
    }


    private fun onlyInputSerializer(): Long {
        var obj: LoginPluginResponse

        val time = measureTimeMillis {
            val bytes = TranslatorAPI.encodeToByteArray(testObject, LoginPluginResponse)
            obj = TranslatorAPI.decodeFromByteArray(bytes, LoginPluginResponse)
        }

        assertEquals(testObject.messageId, obj.messageId)
        assertEquals(testObject.successful, obj.successful)
        Assertions.assertArrayEquals(testObject.data, obj.data)

        return time
    }
}
