package com.android_automated_plant_care.presentation.createGrowingAreaScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CreateGrowingAreaScreen(
    modifier: Modifier = Modifier,
    onClickCreateGrowingArea: () -> Unit
) {
    Column(
        modifier = modifier,
    ) {
        TextField(
            value = "value",
            onValueChange = {}
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = "value",
            onValueChange = {}
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = "value",
            onValueChange = {}
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun CreateGrowingAreaScreenPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        CreateGrowingAreaScreen() {

        }
    }
}