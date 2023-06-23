package com.android_automated_plant_care.repositories

import java.io.Serializable

data class ApiGrowingArea(
    val id: String,
    val name: String,
    val apiSensorData: ApiSensorData,
    val plantType: String,
) : Serializable
