package com.android_automated_plant_care.presentation.listGrowingAreaScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.android_automated_plant_care.domain.mappers.GrowingAreaMapper
import com.android_automated_plant_care.domain.models.GrowingArea
import com.android_automated_plant_care.presentation.mainScreen.Screens
import com.android_automated_plant_care.repositories.InMemoryCache
import com.android_automated_plant_care.repositories.ServerProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ListGrowingAreaScreen(
    navController: NavController,
    onClickCreateGrowingArea: () -> Unit,
) {
    val growingAreas = remember {
        mutableStateOf(listOf<GrowingArea>())
    }

    Scaffold(
        modifier = Modifier.padding(horizontal = 16.dp),
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                CoroutineScope(Dispatchers.IO).launch {
                    val apiItems = ServerProvider.growingAreasRepository.getApiGrowingAreas()
                    val items = apiItems?.map(GrowingAreaMapper::apiGrowingAreaToGrowingArea) ?: listOf()
                    InMemoryCache.addGrowingAreas(items)
                    growingAreas.value = items
                }

                items(
                    items = growingAreas.value,
                    itemContent = { growingAreaItem ->
                        Spacer(modifier = Modifier.height(16.dp))
                        GrowingAreaItem(
                            growingArea = growingAreaItem,
                            onClickItem = {
                                navController.navigate("${Screens.DETAILS_GROWING_AREA}/${growingAreaItem.id}")
                            }
                        )
                    },
                )
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                FloatingActionButton(
                    onClick = onClickCreateGrowingArea,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Добавить новую грядку"
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun PreviewListGrowingAreaScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        ListGrowingAreaScreen(
            navController = rememberNavController(),
            onClickCreateGrowingArea = {},
        )
    }
}