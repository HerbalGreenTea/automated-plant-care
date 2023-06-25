package com.android_automated_plant_care.domain.mappers

import com.android_automated_plant_care.domain.models.GrowingArea
import com.android_automated_plant_care.domain.models.PlantType
import com.android_automated_plant_care.repositories.ApiGrowingArea

object GrowingAreaMapper {

    fun growingAreaToApiGrowingArea(growingArea: GrowingArea): ApiGrowingArea {
        return ApiGrowingArea(
            id = growingArea.id,
            name = growingArea.name,
            apiSensorData = SensorDataMapper.sensorDataToApiSensorData(growingArea.sensorData),
            plantType = growingArea.plantType.name,
            plantCondition = growingArea.plantCondition,
        )
    }

    fun apiGrowingAreaToGrowingArea(apiGrowingArea: ApiGrowingArea): GrowingArea {
        return GrowingArea(
            id = apiGrowingArea.id,
            name = apiGrowingArea.name,
            sensorData = SensorDataMapper.apiSensorDataToSensorData(apiGrowingArea.apiSensorData),
            plantType = PlantType.valueOf(apiGrowingArea.plantType),
            plantCondition = apiGrowingArea.plantCondition,
        )
    }
}