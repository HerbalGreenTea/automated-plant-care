package automated_plant_care.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiGrowingArea(
    val id: String,
    val name: String,
    val apiSensorData: ApiSensorData,
    val plantType: String,
)
