package com.example.android_automated_plant_care.presentation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold() {
        val value = it

        NavHost(
            navController = navController,
            startDestination = MainDestination.HOME_ROUTE,
        ) {
            composable(MainDestination.HOME_ROUTE) {
                HomeScreen()
//                HomeScreen(
//                    onClick = {navController.navigate(MainDestination.CREATE_GROWING_AREA)}
//                )
            }
            composable(MainDestination.CREATE_GROWING_AREA) {
                CreateGrowingAreaScreen(
                    onClick = { navController.navigate(MainDestination.HOME_ROUTE) }
                )
            }
        }
    }
}

object MainDestination {
    const val HOME_ROUTE = "Home"
    const val CREATE_GROWING_AREA = "CreateGrowingArea"
}