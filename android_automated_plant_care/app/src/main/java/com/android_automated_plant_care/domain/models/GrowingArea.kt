package com.android_automated_plant_care.domain.models

data class GrowingArea(
    val id: String,
    val name: String,
    val sensorData: SensorData,
    val plantType: PlantType,
)
