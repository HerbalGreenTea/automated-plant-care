package com.android_automated_plant_care.repositories

import com.android_automated_plant_care.domain.models.GrowingArea
import com.android_automated_plant_care.domain.models.Humidity
import com.android_automated_plant_care.domain.models.Illumination
import com.android_automated_plant_care.domain.models.PlantType
import com.android_automated_plant_care.domain.models.SensorData
import com.android_automated_plant_care.domain.models.WaterLevel
import java.util.UUID

object InMemoryCache {

    private val growingAreas = mutableListOf(
        GrowingArea(
            id = UUID.randomUUID().toString(),
            name = "Грядка с фиалками",
            sensorData = SensorData(
                humidity = Humidity.MEDIUM,
                waterLevel = WaterLevel.HIGH,
                illumination = Illumination.MEDIUM,
            ),
            plantType = PlantType.VIOLETS,
        ),
        GrowingArea(
            id = UUID.randomUUID().toString(),
            name = "Грядка с помидорами",
            sensorData = SensorData(
                humidity = Humidity.LOW,
                waterLevel = WaterLevel.LOW,
                illumination = Illumination.HIGH,
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
                humidity = Humidity.MEDIUM,
                waterLevel = WaterLevel.MEDIUM,
                illumination = Illumination.MEDIUM,
            ),
            plantType = PlantType.VIOLETS,
        )
    }
}