package com.android_automated_plant_care.repositories

data class ApiGrowingArea(
    val id: String,
    val name: String,
    val apiSensorData: ApiSensorData,
    val plantType: String,
)
