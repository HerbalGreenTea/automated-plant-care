package com.android_automated_plant_care.repositories

import retrofit2.Response
import retrofit2.http.GET

interface SensorApi {

    @GET("sensorData")
    suspend fun getSensorData(): Response<SensorData>
}