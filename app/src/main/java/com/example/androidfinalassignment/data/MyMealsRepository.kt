package com.example.androidfinalassignment.data

import com.example.androidfinalassignment.domain.MealsResponse
import com.example.androidfinalassignment.network.MealsApiService
import okhttp3.ResponseBody
import retrofit2.Response

interface MyMealsRepository {

    suspend fun getMealsDay(
        timeFrame: String,
        targetCalories: Int,
        offset: String,
        exclude: String
    ) : MealsResponse

    suspend fun getMealInfo(
        id: Int,
        includeNutrition: Boolean
    ) : Response<ResponseBody>

    suspend fun getAutocompletedTitleRecipe(
        query: String,
        number: Int
    ) : Response<ResponseBody>

}

class NetworkMyMealsRepository(private val mealsApiService: MealsApiService) : MyMealsRepository {
    override suspend fun getMealsDay(
        timeFrame: String,
        targetCalories: Int,
        offset: String,
        exclude: String
    ): MealsResponse {
        return mealsApiService.getMealsDay(
            timeFrame = timeFrame,
            targetCalories = targetCalories,
            offset = offset,
            exclude = exclude
        )
    }

    override suspend fun getMealInfo(id: Int, includeNutrition: Boolean): Response<ResponseBody> {
        return mealsApiService.getMealInfo(
            id = id,
            includeNutrition = includeNutrition
        )
    }

    override suspend fun getAutocompletedTitleRecipe(
        query: String,
        number: Int
    ): Response<ResponseBody> {
        return mealsApiService.getAutocompletedTitleRecipe(
            query = query,
            number = number
        )
    }

}