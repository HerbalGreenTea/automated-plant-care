package com.android_automated_plant_care.presentation.mainScreen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android_automated_plant_care.presentation.createGrowingAreaScreen.CreateGrowingAreaScreen
import com.android_automated_plant_care.presentation.detailsGrowingAreaScreen.DetailsGrowingAreaScreen
import com.android_automated_plant_care.presentation.listGrowingAreaScreen.ListGrowingAreaScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.LIST_GROWING_AREA,
    ) {
        composable(Screens.LIST_GROWING_AREA) {
            ListGrowingAreaScreen(
                onClickCreateGrowingArea = { navController.navigate(Screens.CREATE_GROWING_AREA) },
                onClickItemGrowingArea = { navController.navigate(Screens.DETAILS_GROWING_AREA_SCREEN) },
            )
        }
        composable(Screens.CREATE_GROWING_AREA) {
            CreateGrowingAreaScreen(
                onClickCreateGrowingArea = { navController.navigate(Screens.LIST_GROWING_AREA) }
            )
        }
        composable(Screens.DETAILS_GROWING_AREA_SCREEN) {
            DetailsGrowingAreaScreen()
        }
    }
}

object Screens {
    const val LIST_GROWING_AREA = "listGrowingArea"
    const val CREATE_GROWING_AREA = "createGrowingArea"
    const val DETAILS_GROWING_AREA_SCREEN = "detailsGrowingArea"
}