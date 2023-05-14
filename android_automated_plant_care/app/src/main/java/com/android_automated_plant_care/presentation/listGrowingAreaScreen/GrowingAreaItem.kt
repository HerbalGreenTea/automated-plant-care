package com.android_automated_plant_care.presentation.listGrowingAreaScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android_automated_plant_care.domain.GrowingArea
import com.android_automated_plant_care.domain.PlantType
import com.android_automated_plant_care.repositories.SensorData

@Composable
fun GrowingAreaItem(
    growingArea: GrowingArea,
    onClickItem: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                border = BorderStroke(1.dp, Color.Green),
                shape = RoundedCornerShape(10)
            )
            .padding(8.dp)
            .clickable { onClickItem() }
    ) {
        Text(text = growingArea.name)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = growingArea.sensorData.humidity.toString())
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = growingArea.plantType.localName)
    }
}

@Preview
@Composable
fun PreviewGrowingAreaItem() {

    val growingArea = GrowingArea(
        name = "Грядка с фиалками",
        sensorData = SensorData(
            humidity = 10,
            waterLevel = 20,
            illumination = 30,
        ),
        plantType = PlantType.VIOLETS,
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        GrowingAreaItem(
            growingArea = growingArea,
            onClickItem = {},
        )
    }
}