package com.android_automated_plant_care.repositories

import kotlinx.coroutines.delay
import retrofit2.Response

class ServerRepositoryImpl(private val serverApi: ServerApi) {

    companion object {
        private const val TIMEOUT = 1000L
    }

    suspend fun getServerData(): ServerData? = performSuccess { serverApi.getData() }

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