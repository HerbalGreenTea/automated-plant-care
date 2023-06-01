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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android_automated_plant_care.domain.PlantType
import com.android_automated_plant_care.presentation.theme.Green
import com.android_automated_plant_care.presentation.theme.Silver

@Composable
fun CreateGrowingAreaScreen(
    modifier: Modifier = Modifier,
    onClickCreateGrowingArea: () -> Unit
) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var expanded by remember { mutableStateOf(false) }
    var selectedPlantType by remember { mutableStateOf(PlantType.DEFAULT.localName) }

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
                        Text("название")
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
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
                                    contentDescription = "select plant type",
                                )
                                Text(text = selectedPlantType)
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
                            DropdownMenuItem(onClick = {
                                selectedPlantType = PlantType.TOMATOES.localName
                            }) {
                                Text(text = PlantType.TOMATOES.localName)
                            }
                            Divider()
                            DropdownMenuItem(onClick = {
                                selectedPlantType = PlantType.VIOLETS.localName
                            }) {
                                Text(text = PlantType.VIOLETS.localName)
                            }
                        }
                    )
                }
            }
        },
        bottomBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = onClickCreateGrowingArea,
                    content = {
                        Text(
                            text = "Создать площадь выращивания",
                            fontSize = 18.sp
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Green,
                        contentColor = Silver
                    )
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