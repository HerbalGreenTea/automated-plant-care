package com.android_automated_plant_care.repositories

import kotlinx.coroutines.delay
import retrofit2.Response

class GrowingAreasRepositoryImpl(private val GrowingAreasApi: GrowingAreasApi) {

    companion object {
        private const val TIMEOUT = 1000L
    }

    suspend fun getApiGrowingAreas(): List<ApiGrowingArea>? = performSuccess { GrowingAreasApi.getGrowingAreas() }

    suspend fun putApiGrowingArea(apiGrowingArea: ApiGrowingArea) = GrowingAreasApi.putGrowingArea(apiGrowingArea)

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