package automated_plant_care.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiSensorData(
    val humidity: String,
    val waterLevel: String,
    val illumination: String,
)
