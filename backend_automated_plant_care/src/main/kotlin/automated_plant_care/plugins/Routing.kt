package automated_plant_care.plugins

import automated_plant_care.SensorData
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    val sensorDataRepository = SensorDataRepository()

    routing {
        get("/sensorData") {
            val sensorData = sensorDataRepository.getSensorData()
            call.respond(sensorData)
        }
    }
}

class SensorDataRepository() {

    fun getSensorData(): SensorData {
        return SensorData(
            humidity = 100,
            waterLevel = 200,
            illumination = 300,
        )
    }
}
