package com.android_automated_plant_care.repositories

import com.android_automated_plant_care.domain.GrowingArea
import com.android_automated_plant_care.domain.PlantType
import java.util.UUID

object InMemoryCache {

    private val growingAreas = mutableListOf(
        GrowingArea(
            id = UUID.randomUUID().toString(),
            name = "Грядка с фиалками",
            sensorData = SensorData(
                humidity = 10,
                waterLevel = 20,
                illumination = 30,
            ),
            plantType = PlantType.VIOLETS,
        ),
        GrowingArea(
            id = UUID.randomUUID().toString(),
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

    fun getGrowingAreaById(id: String?): GrowingArea? {
        growingAreas.forEach {
            if (it.id == id) {
                return it
            }
        }
        return null
    }

    fun addGrowingArea(growingArea: GrowingArea) {
        growingAreas.add(growingArea)
    }

    fun getDefaultGrowingArea(): GrowingArea {
        return GrowingArea(
            id = UUID.randomUUID().toString(),
            name = "Имя площади с растениями",
            sensorData = SensorData(
                humidity = 10,
                waterLevel = 20,
                illumination = 30,
            ),
            plantType = PlantType.VIOLETS,
        )
    }
}