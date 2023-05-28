package com.android_automated_plant_care.domain

import com.android_automated_plant_care.repositories.SensorData

data class GrowingArea(
    val id: String,
    val name: String,
    val sensorData: SensorData,
    val plantType: PlantType,
)
