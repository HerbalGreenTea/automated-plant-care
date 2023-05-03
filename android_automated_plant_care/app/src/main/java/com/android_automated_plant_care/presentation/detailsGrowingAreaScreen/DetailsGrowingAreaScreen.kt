package com.android_automated_plant_care.presentation.detailsGrowingAreaScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailsGrowingAreaScreen() {
    Column {
        Text(text = "Details Growing Area")
        Spacer(modifier = Modifier.height(32.dp))
    }
}