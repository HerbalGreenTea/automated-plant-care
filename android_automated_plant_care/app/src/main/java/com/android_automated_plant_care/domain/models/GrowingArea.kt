package com.android_automated_plant_care.domain.models

import com.android_automated_plant_care.repositories.ApiSensorData

data class GrowingArea(
    val id: String,
    val name: String,
    val apiSensorData: ApiSensorData,
    val plantType: PlantType,
)
