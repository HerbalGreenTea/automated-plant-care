package com.android_automated_plant_care.presentation.controls

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.android_automated_plant_care.domain.models.PlantType
import com.android_automated_plant_care.presentation.theme.Green
import com.android_automated_plant_care.presentation.theme.Silver

@Composable
fun GrowingAreaDropDownButton(
    startValue: String,
    onValueChange: (String) -> Unit,
    contentDescription: String,
    valuesForDropDownMenu: List<String>,
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { expanded = true },
            content = {
                Row() {
                    Icon(
                        tint = Green,
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = contentDescription,
                    )
                    Text(text = startValue)
                }
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Silver,
                contentColor = Green,
            )
        )
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = { expanded = false },
            content = {
                valuesForDropDownMenu.forEach {
                    DropdownMenuItem(
                        onClick = {
                            onValueChange(it)
                            expanded = false
                        },
                        content = {
                            Text(text = it)
                        }
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewGrowingAreaDropDownButton() {

    var selectedPlantType by remember { mutableStateOf(PlantType.DEFAULT.localName) }

    Box(
        modifier = Modifier.background(color = Color.White)
    ) {
        GrowingAreaDropDownButton(
            startValue = selectedPlantType,
            onValueChange = { selectedPlantType = it },
            contentDescription = "selected plane type",
            valuesForDropDownMenu = PlantType.values().map { it.localName },
        )
    }
}