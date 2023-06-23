package com.android_automated_plant_care.repositories

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface GrowingAreasApi {

    @GET("growingAreas")
    suspend fun getGrowingAreas(): Response<List<ApiGrowingArea>>

    @PUT("growingArea")
    suspend fun putGrowingArea(@Body apiGrowingArea: ApiGrowingArea)
}