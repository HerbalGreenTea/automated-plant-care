package automated_plant_care

import automated_plant_care.plugins.configureRouting
import automated_plant_care.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module).start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureSerialization()
//    configureSocket()
}
