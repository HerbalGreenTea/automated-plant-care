package com.android_automated_plant_care.presentation.detailsGrowingAreaScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android_automated_plant_care.domain.GrowingArea
import com.android_automated_plant_care.repositories.InMemoryCache

@Composable
fun DetailsGrowingAreaScreen(
    growingArea: GrowingArea,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "подробное описание грядки")
        Spacer(modifier = Modifier.height(16.dp))

        with(growingArea) {
            Text(text = name)
            Text(text = "состояние растения: ")
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