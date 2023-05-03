package com.android_automated_plant_care.repositories

import retrofit2.Response
import retrofit2.http.GET

interface ServerApi {

    @GET("data")
    suspend fun getData(): Response<ServerData>
}