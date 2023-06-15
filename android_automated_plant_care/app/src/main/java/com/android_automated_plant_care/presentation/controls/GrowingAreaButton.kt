package com.android_automated_plant_care.presentation.controls

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android_automated_plant_care.presentation.theme.Green
import com.android_automated_plant_care.presentation.theme.Silver

@Composable
fun GrowingAreaButton(
    onClick: () -> Unit,
    text: String
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Silver,
            contentColor = Green
        ),
        border = BorderStroke(1.dp, Green),
        content = {
            Text(text = text)
        }
    )
}

@Preview
@Composable
fun PreviewGrowingAreaButton() {
    Box(modifier = Modifier.background(color = Color.White)) {
        GrowingAreaButton(
            onClick = {},
            text = "кнопка"
        )
    }
}