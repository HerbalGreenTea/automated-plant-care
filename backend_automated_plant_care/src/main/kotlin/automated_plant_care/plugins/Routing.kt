package automated_plant_care.plugins

import automated_plant_care.SensorData
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        get("/sensorData") {
            val sensorData = SensorData(
                humidity = 100,
                waterLevel = 200,
                illumination = 300,
            )

            call.respond(sensorData)
        }
    }
}
