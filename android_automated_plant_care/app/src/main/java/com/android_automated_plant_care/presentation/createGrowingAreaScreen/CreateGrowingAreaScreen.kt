package com.android_automated_plant_care.presentation.createGrowingAreaScreen

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
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android_automated_plant_care.domain.mappers.SensorDataMapper
import com.android_automated_plant_care.domain.models.GrowingArea
import com.android_automated_plant_care.domain.models.Humidity
import com.android_automated_plant_care.domain.models.Illumination
import com.android_automated_plant_care.domain.models.PlantType
import com.android_automated_plant_care.domain.models.SensorData
import com.android_automated_plant_care.domain.models.WaterLevel
import com.android_automated_plant_care.presentation.controls.GrowingAreaButton
import com.android_automated_plant_care.presentation.controls.GrowingAreaDropDownButton
import com.android_automated_plant_care.repositories.InMemoryCache
import java.util.UUID

@Composable
fun CreateGrowingAreaScreen(
    modifier: Modifier = Modifier,
    onClickCreateGrowingArea: () -> Unit
) {
    var name by remember { mutableStateOf(TextFieldValue("")) }

    var selectedPlantType by remember { mutableStateOf(PlantType.DEFAULT) }
    var selectedHumidity by remember { mutableStateOf(Humidity.LOW) }
    var selectedIllumination by remember { mutableStateOf(Illumination.LOW) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        content = { paddingValues ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = { name = it },
                    placeholder = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "название",
                            textAlign = TextAlign.Center,
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "Укажите тип растения и какие должны быть параметры влажности и освещенность",
                        textAlign = TextAlign.Center,
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                GrowingAreaDropDownButton(
                    startValue = selectedPlantType.localName,
                    onValueChange = { selectedPlantType = SensorDataMapper.getPlaneTypeByLocalName(it) },
                    contentDescription = "selected plane type",
                    valuesForDropDownMenu = PlantType.values().map { it.localName },
                )
                Spacer(modifier = Modifier.height(4.dp))
                GrowingAreaDropDownButton(
                    startValue = selectedHumidity.localName,
                    onValueChange = { selectedHumidity = SensorDataMapper.getHumidityByLocalName(it) },
                    contentDescription = "selected humidity",
                    valuesForDropDownMenu = Humidity.values().map { it.localName },
                )
                Spacer(modifier = Modifier.height(4.dp))
                GrowingAreaDropDownButton(
                    startValue = selectedIllumination.localName,
                    onValueChange = { selectedIllumination = SensorDataMapper.getIlluminationByLocalName(it) },
                    contentDescription = "selected illumination",
                    valuesForDropDownMenu = Illumination.values().map { it.localName },
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
        },
        bottomBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                GrowingAreaButton(
                    onClick = {
                        InMemoryCache.addGrowingArea(
                            GrowingArea(
                                id = UUID.randomUUID().toString(),
                                name = name.text,
                                sensorData = SensorData(
                                    humidity = selectedHumidity,
                                    illumination = selectedIllumination,
                                    waterLevel = WaterLevel.HIGH,
                                ),
                                plantType = selectedPlantType
                            )
                        )
                        onClickCreateGrowingArea()
                    },
                    text = "создать"
                )
            }
        }
    )
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