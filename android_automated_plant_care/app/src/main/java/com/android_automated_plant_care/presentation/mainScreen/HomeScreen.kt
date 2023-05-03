package com.android_automated_plant_care.presentation.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android_automated_plant_care.repositories.ServerProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun HomeScreen() {
    val text = remember { mutableStateOf("Home Screen") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = text.value)
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    val data = ServerProvider.serverRepository.getServerData()

                    withContext(Dispatchers.Main) {
                        text.value = data?.name ?: "нет данных"
                    }
                }
            }
        ) {
            Text(text = "Second Screen")
        }
    }
}