package com.android_automated_plant_care.presentation.detailsGrowingAreaScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android_automated_plant_care.domain.GrowingArea
import com.android_automated_plant_care.repositories.InMemoryCache

@Composable
fun DetailsGrowingAreaScreen(
    growingArea: GrowingArea,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Описание", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(32.dp))

        with(growingArea) {
            Text(text = "Название: $name")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Тип растения: ${plantType.localName}")
            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "Данные с сенсоров", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Влажность: ${sensorData.humidity}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Уровень воды: ${sensorData.waterLevel}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Освещенность: ${sensorData.illumination}")
        }
    }
}


@Preview
@Composable
fun PreviewDetailsGrowingScreen() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        DetailsGrowingAreaScreen(growingArea = InMemoryCache.getDefaultGrowingArea())
    }
}