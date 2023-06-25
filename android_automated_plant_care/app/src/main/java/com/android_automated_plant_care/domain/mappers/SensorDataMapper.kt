package com.android_automated_plant_care.domain.mappers

import com.android_automated_plant_care.domain.models.Humidity
import com.android_automated_plant_care.domain.models.Illumination
import com.android_automated_plant_care.domain.models.PlantType
import com.android_automated_plant_care.domain.models.SensorData
import com.android_automated_plant_care.domain.models.WaterLevel
import com.android_automated_plant_care.repositories.ApiSensorData

object SensorDataMapper {

    fun sensorDataToApiSensorData(sensorData: SensorData): ApiSensorData {
        return ApiSensorData(
            humidity = sensorData.humidity.name,
            waterLevel = sensorData.waterLevel.name,
            illumination = sensorData.illumination.name,
        )
    }

    fun apiSensorDataToSensorData(apiSensorData: ApiSensorData): SensorData {
        return SensorData(
            humidity = Humidity.valueOf(apiSensorData.humidity),
            waterLevel = WaterLevel.valueOf(apiSensorData.waterLevel),
            illumination = Illumination.valueOf(apiSensorData.illumination),
        )
    }

    fun getPlaneTypeByLocalName(localName: String): PlantType {
        return when {
            PlantType.DEFAULT.localName == localName -> PlantType.DEFAULT
            PlantType.VIOLETS.localName == localName -> PlantType.VIOLETS
            PlantType.MONSTERA.localName == localName -> PlantType.MONSTERA
            PlantType.PELARGONIUM.localName == localName -> PlantType.PELARGONIUM
            else -> PlantType.DEFAULT
        }
    }

    fun getHumidityByLocalName(localName: String): Humidity {
        return when {
            Humidity.LOW.localName == localName -> Humidity.LOW
            Humidity.MEDIUM.localName == localName -> Humidity.MEDIUM
            Humidity.HIGH.localName == localName -> Humidity.HIGH
            else -> Humidity.LOW
        }
    }

    fun getIlluminationByLocalName(localName: String): Illumination {
        return when {
            Illumination.LOW.localName == localName -> Illumination.LOW
            Illumination.MEDIUM.localName == localName -> Illumination.MEDIUM
            Illumination.HIGH.localName == localName -> Illumination.HIGH
            else -> Illumination.LOW
        }
    }
}