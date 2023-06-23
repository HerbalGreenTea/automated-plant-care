package automated_plant_care.plugins

import automated_plant_care.InMemoryCache
import automated_plant_care.models.ApiGrowingArea
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/growingAreas") {
            val growingAreas = InMemoryCache.getGrowingAreas()
            call.respond(growingAreas)
        }

        put("/growingArea") {
            val growingArea = call.receive(ApiGrowingArea::class)
            InMemoryCache.putGrowingArea(growingArea)
            call.respond(HttpStatusCode.OK)
        }
    }
}
