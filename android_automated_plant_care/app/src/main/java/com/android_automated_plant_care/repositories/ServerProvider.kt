package com.android_automated_plant_care.repositories

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServerProvider {
    private val URL = "http://192.168.1.106:8080"

    private val okHttpClient = OkHttpClient.Builder().build()
    private val gson = GsonBuilder().create()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private val sensorApi: SensorApi = retrofit.create(SensorApi::class.java)

    val sensorRepository = SensorRepositoryImpl(sensorApi)
}