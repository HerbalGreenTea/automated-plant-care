package automated_plant_care.plugins

import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.server.application.*
import io.ktor.utils.io.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun Application.configureSocket() {
    CustomSocket.startListen()
}

object CustomSocket {
    fun startListen() {
        runBlocking(Dispatchers.IO) {
            val selectorManager = SelectorManager(Dispatchers.IO)
            val serverSocket = aSocket(selectorManager).tcp().bind("192.168.1.105", 8081)
            var isListen = true

            while (isListen) {
                val socket = serverSocket.accept()

                launch {
                    val receiveChannel = socket.openReadChannel()

                    try {
                        while (true) {
                            val data = receiveChannel.readUTF8Line(1024)


                        }
                    } catch (e: Throwable) {
                        withContext(Dispatchers.IO) {
                            socket.close()
                        }
                        isListen = false
                    }
                }
            }
        }
    }

    fun sendData(data: String) {
        runBlocking(Dispatchers.IO) {
            val selectorManager = SelectorManager(Dispatchers.IO)
            val socket = aSocket(selectorManager).tcp().connect("192.168.1.105", 8080)

            val sendChannel = socket.openWriteChannel(autoFlush = true)

            try {
                sendChannel.writeStringUtf8(data)
            } catch (e: Throwable) {
                withContext(Dispatchers.IO) {
                    socket.close()
                    selectorManager.close()
                }
                println("Socket closed")
            }

            withContext(Dispatchers.IO) {
                socket.close()
                selectorManager.close()
            }
        }
    }
}