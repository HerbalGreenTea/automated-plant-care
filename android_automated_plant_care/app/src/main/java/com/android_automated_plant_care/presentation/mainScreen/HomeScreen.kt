package com.android_automated_plant_care.presentation.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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
                    val sensorData = ServerProvider.sensorRepository.getSensorData()

                    withContext(Dispatchers.Main) {
                        text.value = sensorData?.humidity.toString()
                    }
                }
            }
        ) {
            Text(text = "Second Screen")
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        HomeScreen()
    }
}