package com.android_automated_plant_care.domain.mappers

import com.android_automated_plant_care.domain.models.Humidity
import com.android_automated_plant_care.domain.models.Illumination
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
}