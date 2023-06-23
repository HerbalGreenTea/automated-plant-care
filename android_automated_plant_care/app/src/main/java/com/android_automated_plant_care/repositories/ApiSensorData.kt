package com.android_automated_plant_care.repositories

import java.io.Serializable

data class ApiSensorData(
    val humidity: String,
    val waterLevel: String,
    val illumination: String,
) : Serializable
