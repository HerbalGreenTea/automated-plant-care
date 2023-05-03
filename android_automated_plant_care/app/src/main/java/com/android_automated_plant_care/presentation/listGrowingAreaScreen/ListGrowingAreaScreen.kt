package com.android_automated_plant_care.presentation.listGrowingAreaScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListGrowingAreaScreen(
    onClickCreateGrowingArea: () -> Unit,
    onClickItemGrowingArea: () -> Unit,
) {
    Column {
        Text("List Screen")
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { onClickCreateGrowingArea() }) {
            Text(text = "Create Growing Area")
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { onClickItemGrowingArea() }) {
            Text(text = "Details Growing Area")
        }
    }
}