package com.example.androidfinalassignment.network

import com.example.androidfinalassignment.domain.AutocompleteMeal
import com.example.androidfinalassignment.domain.MealsResponse
import retrofit2.Retrofit
import retrofit2.http.GET

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * An interface to communicate with the API using Retrofit.
 */

interface MealsApiService {

    /**
     * A function to get a list of meals for a day.
     * @param timeFrame The time frame for which the meals are generated.
     * @param targetCalories The target calories for the day.
     * @param offset The offset for the query.
     * @param exclude The ingredients to be excluded from the meals.
     */
    @GET("recipes/mealplans/generate")
    suspend fun getMealsDay(
        @Query("timeFrame") timeFrame: String,
        @Query("targetCalories") targetCalories: Int,
        @Query("diet") offset: String,
        @Query("exclude") exclude: String
    ) : MealsResponse

    /**
     * A function to get more information about a meal.
     * @param id The id of the meal.
     * @param includeNutrition Whether to include nutrition information.
     */
    @GET("recipes/{id}/information")
    suspend fun getMealInfo(
        @Path("id") id: Int,
        @Query("includeNutrition") includeNutrition: Boolean
    ) : Response<ResponseBody>

    /**
     * A function to autocomplete a title for a meal.
     * @param query The query to autocomplete.
     * @param number The number of results to return.
     */
    @GET("recipes/autocomplete")
    suspend fun getAutocompletedTitleRecipe(
        @Query("query") query: String,
        @Query("number") number: Int
    ) : Response<ResponseBody>

}
