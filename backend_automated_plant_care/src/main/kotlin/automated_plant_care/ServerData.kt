package automated_plant_care

import kotlinx.serialization.Serializable

@Serializable
data class ServerData(
    val uuid: String,
    val name: String,
)
