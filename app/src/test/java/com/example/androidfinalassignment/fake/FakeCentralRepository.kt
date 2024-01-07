package com.example.androidfinalassignment.fake

import com.example.androidfinalassignment.data.CentralRepository
import com.example.androidfinalassignment.data.User
import com.example.androidfinalassignment.domain.MealsResponse
import com.example.androidfinalassignment.domain.RecipeResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class FakeCentralRepository : CentralRepository {
    override fun getAllUsersStream(): Flow<List<User>> {
        TODO("Not yet implemented")
    }

    override fun getUserStream(id: Int): Flow<User> {
        TODO("Not yet implemented")
    }

    override suspend fun insertUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTable() {
        TODO("Not yet implemented")
    }

    override suspend fun getMealsDay(
        timeFrame: String,
        targetCalories: Int,
        offset: String,
        exclude: String
    ): MealsResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getMealInfo(id: Int, includeNutrition: Boolean): Response<ResponseBody> {
        TODO("Not yet implemented")
    }

    override suspend fun getAutocompletedTitleRecipe(
        query: String,
        number: Int
    ): Response<ResponseBody> {
        return Response.success(FakeData.recipeResponse1Json.toResponseBody(null))
    }

    override suspend fun retrieveSavedUsersMealPlan(): List<RecipeResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllUsers() {
        TODO("Not yet implemented")
    }

    override suspend fun checkIfUserExists(): Boolean {
        return true
    }

    override suspend fun getNewMeals(): List<RecipeResponse> {
        return listOf(FakeData.recipeResponse1, FakeData.recipeResponse2, FakeData.recipeResponse3)
    }

    override suspend fun getRecipe(id: Int): RecipeResponse {
        return FakeData.recipeResponse1
    }
}