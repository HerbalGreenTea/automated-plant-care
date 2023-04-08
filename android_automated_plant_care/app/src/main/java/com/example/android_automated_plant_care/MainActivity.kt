package com.example.android_automated_plant_care

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv = findViewById<TextView>(R.id.tv_main)
        val btnMain = findViewById<Button>(R.id.btn_main)

        btnMain.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val data = ServerProvider.serverRepository.getServerData()

                withContext(Dispatchers.Main) {
                    tv.text = data?.name ?: "нет данных"
                }
            }
        }
    }
}

object ServerProvider {
    private val URL = "http://192.168.1.106:8080"

    private val okHttpClient = OkHttpClient.Builder().build()
    private val gson = GsonBuilder().create()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private val serverApi: ServerApi = retrofit.create(ServerApi::class.java)

    val serverRepository = ServerRepositoryImpl(serverApi)
}