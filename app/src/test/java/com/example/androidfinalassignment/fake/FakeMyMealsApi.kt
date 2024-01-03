package com.example.androidfinalassignment.fake

import com.example.androidfinalassignment.domain.MealsResponse
import com.example.androidfinalassignment.domain.RecipeResponse
import com.example.androidfinalassignment.network.MealsApiService
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class FakeMyMealsApi : MealsApiService {

    val json = JsonHelper()
    override suspend fun getMealsDay(
        timeFrame: String,
        targetCalories: Int,
        offset: String,
        exclude: String
    ): MealsResponse {
        return FakeData.mealResponse1
    }

    override suspend fun getMealInfo(id: Int, includeNutrition: Boolean): Response<ResponseBody> {
        return Response.success(FakeData.recipeResponse1Json.toResponseBody(null))
    }

    override suspend fun getAutocompletedTitleRecipe(
        query: String,
        number: Int
    ): Response<ResponseBody> {
        return Response.success(FakeData.recipeResponse1Json.toResponseBody(null))
    }
}

class JsonHelper() {
    val json = Json { ignoreUnknownKeys = true
        isLenient = true}

    fun getObjectFromString(jsonString: String): RecipeResponse {
        return json.decodeFromString(RecipeResponse.serializer(), jsonString)
    }
}