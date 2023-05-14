package com.android_automated_plant_care.repositories

import com.android_automated_plant_care.domain.GrowingArea
import com.android_automated_plant_care.domain.PlantType

object InMemoryCache {

    private val growingAreas = mutableListOf<GrowingArea>(
        GrowingArea(
            name = "Грядка с фиалками",
            sensorData = SensorData(
                humidity = 10,
                waterLevel = 20,
                illumination = 30,
            ),
            plantType = PlantType.VIOLETS,
        ),
        GrowingArea(
            name = "Грядка с помидорами",
            sensorData = SensorData(
                humidity = 100,
                waterLevel = 200,
                illumination = 300,
            ),
            plantType = PlantType.TOMATOES,
        )
    )

    fun getGrowingAreas(): List<GrowingArea> {
        return growingAreas
    }

    fun addGrowingArea(growingArea: GrowingArea) {
        growingAreas.add(growingArea)
    }
}