package automated_plant_care

import automated_plant_care.models.ApiGrowingArea
import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.utils.io.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object InMemoryCache {

    private val growingAreas = mutableListOf<ApiGrowingArea>()

    fun getGrowingAreas(): List<ApiGrowingArea> {
        return growingAreas
    }

    suspend fun getDataAboutPlant(planType: String): String {
        var predictionResult = ""

        runBlocking(Dispatchers.IO) {
            val readSelectorManager = SelectorManager(Dispatchers.IO)
            val serverSocket = aSocket(readSelectorManager).tcp().bind("192.168.1.105", 8082)

            val selectorManager = SelectorManager(Dispatchers.IO)
            val socket = aSocket(selectorManager).tcp().connect("192.168.1.105", 8081)
            val sendChannel = socket.openWriteChannel(autoFlush = true)

            launch(Dispatchers.IO) {
                while (predictionResult.isBlank()) {
                    val readSocket = serverSocket.accept()
                    val receiveChannel = readSocket.openReadChannel()

                    val greeting = receiveChannel.readUTF8Line()
                    if (greeting != null) {
                        println("greeting: $greeting")
                        if (greeting == "good" || greeting == "bad") {
                            predictionResult = greeting

                            readSocket.close()
                            serverSocket.close()
                            readSelectorManager.close()
                            socket.close()
                            selectorManager.close()
                        }
                    } else {
                        println("Server closed a connection")
                        readSocket.close()
                        serverSocket.close()
                        socket.close()
                        selectorManager.close()
                    }
                }
            }

            sendChannel.writeStringUtf8(planType)
        }

        return predictionResult
    }

    fun putGrowingArea(apiGrowingArea: ApiGrowingArea) {
        growingAreas.add(apiGrowingArea)
    }
}