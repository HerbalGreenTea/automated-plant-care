package automated_plant_care

import kotlinx.serialization.Serializable

@Serializable
data class SensorData(
    val humidity: Int,
    val waterLevel: Int,
    val illumination: Int,
)