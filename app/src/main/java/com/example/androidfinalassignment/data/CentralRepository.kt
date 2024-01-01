package com.example.androidfinalassignment.data

import com.example.androidfinalassignment.domain.Meal
import com.example.androidfinalassignment.domain.MealsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response

interface CentralRepository {
    /**
     * Retrieve all users from the database.
     */
    fun getAllUsersStream(): Flow<List<User>>

    /**
     * Retrieve a user with specified id from the database.
     */
    fun getUserStream(id: Int): Flow<User>

    /**
     * Insert a user in the database.
     */
    suspend fun insertUser(user: User)

    /**
     * Update a user in the database.
     */
    suspend fun updateUser(user: User)

    /**
     * Delete a user in the database.
     */
    suspend fun deleteUser(user: User)

    /**
     * Retrieve 3 meals for a day from the API.
     */

    suspend fun getMealsDay(
        timeFrame: String,
        targetCalories: Int,
        offset: String,
        exclude: String
    ): MealsResponse

    /**
     * Retrieve a meal with specified id from the API.
     */
    suspend fun getMealInfo(
        id: Int,
        includeNutrition: Boolean
    ): Response<ResponseBody>

    /**
     * Retrieve meals that match the query from the API.
     */
    suspend fun getAutocompletedTitleRecipe(
        query: String,
        number: Int
    ): Response<ResponseBody>


    /**
     * Check if the user exists in the database and return the last meal plan.
     */
    suspend fun retrieveSavedUsersMealPlan(): List<Meal>

    /**
     * Delete all users from the database.
     */
    suspend fun deleteAllUsers()

    /**
     * Check if the user exists in the database.
     */
    suspend fun checkIfUserExists() : Boolean

    /**
     * Retrieve new meals for the user.
     */
    suspend fun getNewMeals(): List<Meal>

}
