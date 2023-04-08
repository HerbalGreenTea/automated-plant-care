package automated_plant_care.plugins

import automated_plant_care.ServerData
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        get("/data") {

            val data = ServerData("castom uuid", "castom name")
            call.respond(data)
        }
    }
}
