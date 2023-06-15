package com.android_automated_plant_care.repositories

import kotlinx.coroutines.delay
import retrofit2.Response

class SensorRepositoryImpl(private val SensorApi: SensorApi) {

    companion object {
        private const val TIMEOUT = 1000L
    }

    suspend fun getSensorData(): ApiSensorData? = performSuccess { SensorApi.getSensorData() }

    private suspend fun <T> performSuccess(responseGetter: suspend () -> Response<T>): T? {
        while (true) {
            try {
                val response = responseGetter()

                when {
                    (response.isSuccessful) -> return response.body()
                    (response.code() >= 500) -> delay(TIMEOUT)
                    else -> return null
                }
            } catch (e: Exception) {
                print(e)
                return null
            }
        }
    }
}