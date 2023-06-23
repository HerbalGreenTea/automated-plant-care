package automated_plant_care

import automated_plant_care.models.ApiGrowingArea

object InMemoryCache {

    private val growingAreas = mutableListOf<ApiGrowingArea>()

    fun getGrowingAreas(): List<ApiGrowingArea> {
        return growingAreas
    }

    fun putGrowingArea(apiGrowingArea: ApiGrowingArea) {
        growingAreas.add(apiGrowingArea)
    }
}