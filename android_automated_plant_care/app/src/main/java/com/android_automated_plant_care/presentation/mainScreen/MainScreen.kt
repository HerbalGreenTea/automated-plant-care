package com.android_automated_plant_care.presentation.mainScreen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.android_automated_plant_care.presentation.createGrowingAreaScreen.CreateGrowingAreaScreen
import com.android_automated_plant_care.presentation.detailsGrowingAreaScreen.DetailsGrowingAreaScreen
import com.android_automated_plant_care.presentation.listGrowingAreaScreen.ListGrowingAreaScreen
import com.android_automated_plant_care.repositories.InMemoryCache

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.LIST_GROWING_AREA,
    ) {
        composable(Screens.LIST_GROWING_AREA) {
            ListGrowingAreaScreen(
                navController = navController,
                onClickCreateGrowingArea = { navController.navigate(Screens.CREATE_GROWING_AREA) },
            )
        }
        composable(Screens.CREATE_GROWING_AREA) {
            CreateGrowingAreaScreen(
                onClickCreateGrowingArea = { navController.navigate(Screens.LIST_GROWING_AREA) }
            )
        }
        composable(
            route = "${Screens.DETAILS_GROWING_AREA}/{growingAreaId}",
            arguments = listOf(navArgument("growingAreaId") { type = NavType.StringType })
        ) {
            val growingAreaId = it.arguments?.getString("growingAreaId")
            val growingArea = InMemoryCache.getGrowingAreaById(growingAreaId)

            if (growingArea != null) {
                DetailsGrowingAreaScreen(growingArea = growingArea)
            } else {
                Text(text = "growingAreaId null")
            }
        }
    }
}

object Screens {
    const val LIST_GROWING_AREA = "listGrowingArea"
    const val CREATE_GROWING_AREA = "createGrowingArea"
    const val DETAILS_GROWING_AREA = "detailsGrowingArea"
}