package automated_plant_care.plugins

import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import io.ktor.server.application.*
import java.nio.ByteBuffer

fun Application.configureSocket() {
    val socketIoT = SocketForIoT()
    socketIoT.startListen()
}

class SocketForIoT {
    fun startListen() {
        runBlocking(Dispatchers.IO) {
            val selectorManager = SelectorManager(Dispatchers.IO)
            val serverSocket = aSocket(selectorManager).tcp().bind("192.168.57.120", 8080)
            var isListen = true

            while (isListen) {
                val socket = serverSocket.accept()

                launch {
                    val receiveChannel = socket.openReadChannel()

                    try {
                        while (true) {
                            val name = receiveChannel.readUTF8Line(1024)
                            if (name != null) {
                                println("data: $name")
                            }
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
}