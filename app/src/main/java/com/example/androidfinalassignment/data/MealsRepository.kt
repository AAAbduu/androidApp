package com.example.androidfinalassignment.data

import com.example.androidfinalassignment.domain.MealsResponse
import com.example.androidfinalassignment.network.MealsApiService
import okhttp3.ResponseBody
import retrofit2.Response

interface MealsRepository {

    /**
     * Retrieve 3 meals for a day from the API.
     */

    suspend fun getMealsDay(
        timeFrame: String,
        targetCalories: Int,
        offset: String,
        exclude: String
    ) : MealsResponse

    /**
     * Retrieve a meal with specified id from the API.
     */
    suspend fun getMealInfo(
        id: Int,
        includeNutrition: Boolean
    ) : Response<ResponseBody>

    /**
     * Retrieve meals that match the query from the API.
     */
    suspend fun getAutocompletedTitleRecipe(
        query: String,
        number: Int
    ) : Response<ResponseBody>

}
