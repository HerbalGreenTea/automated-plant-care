package com.example.android_automated_plant_care.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CreateGrowingAreaScreen(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(color = Color.Cyan)
            .fillMaxSize(),

        ) {
        Button(
            onClick = onClick
        ) {
            Text(text = "Кнопка")
        }
    }
}

@Preview
@Composable
fun CreateGrowingAreaScreenPreview() {
    CreateGrowingAreaScreen(
        onClick = {}
    )
}