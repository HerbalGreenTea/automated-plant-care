package com.android_automated_plant_care.presentation.mainScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.android_automated_plant_care.presentation.theme.MainTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    content = { MainScreen() },
                )
            }
        }
    }
}