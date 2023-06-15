package com.android_automated_plant_care.domain.models

data class SensorData(
    val humidity: Humidity,
    val waterLevel: WaterLevel,
    val illumination: Illumination,
)
