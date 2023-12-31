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





interface MealsApiService {

    @Headers("X-RapidAPI-Key: REPLACE_ME")
    @GET("recipes/mealplans/generate")
    suspend fun getMealsDay(
        @Query("timeFrame") timeFrame: String,
        @Query("targetCalories") targetCalories: Int,
        @Query("diet") offset: String,
        @Query("exclude") exclude: String
    ) : MealsResponse

    @Headers("X-RapidAPI-Key: REPLACE_ME")
    @GET("recipes/{id}/information")
    suspend fun getMealInfo(
        @Path("id") id: Int,
        @Query("includeNutrition") includeNutrition: Boolean
    ) : Response<ResponseBody>

    @Headers("X-RapidAPI-Key: REPLACE_ME")
    @GET("recipes/autocomplete")
    suspend fun getAutocompletedTitleRecipe(
        @Query("query") query: String,
        @Query("number") number: Int
    ) : Response<ResponseBody>

}
