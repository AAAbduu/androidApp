package com.example.androidfinalassignment.data

import android.content.Context
import com.example.androidfinalassignment.data.database.UserDao
import com.example.androidfinalassignment.domain.Meal
import com.example.androidfinalassignment.domain.MealsResponse
import com.example.androidfinalassignment.domain.Nutrition
import com.example.androidfinalassignment.domain.RecipeResponse
import com.example.androidfinalassignment.network.MealsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * A repository that handles the work with users and meals.
 */
class CentralUserMealsRepository (private val userDao: UserDao, private val mealsApiService: MealsApiService, context: Context) : CentralRepository{

    override fun getAllUsersStream() = userDao.getAllItems()

    override fun getUserStream(id: Int) = userDao.getUser(id)

    override suspend fun insertUser(user: User) = userDao.insertUser(user)

    override suspend fun updateUser(user: User) = userDao.update(user)

    override suspend fun deleteUser(user: User) = userDao.delete(user)

    override suspend fun delete() {
        userDao.delete()
    }

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
            includeNutrition = includeNutrition)
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

    /**
     * A function to retrieve meals for a day from the API.
     * @param user The user for which the meals are generated.
     */
    private suspend fun retrieveMeals(user: User) : List<RecipeResponse> = withContext(Dispatchers.IO){
        var mealsRetrieved = listOf<RecipeResponse>()
        try {
            val response = getMealsDay(
                timeFrame = "day",
                targetCalories = user.dailyCalories.toInt(),
                offset = "0",
                exclude = user.foodAllergies
            )
            for (meal in response.meals) {
                val mealInfo = getMealInfo(
                    id = meal.id,
                    includeNutrition = true
                )
                mealInfo.body()?.let {

                    val json = Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                    val mealInfo =
                        json.decodeFromString(RecipeResponse.serializer(), it.string())

                    mealsRetrieved += mealInfo
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext mealsRetrieved
    }

    override suspend fun retrieveSavedUsersMealPlan(): List<RecipeResponse> {
        val userFlow = getAllUsersStream()
        val userF = userFlow.firstOrNull()
        val user = userF?.firstOrNull()

        if(user != null && user.dailyRecipes.isNotEmpty()){
            return user.dailyRecipes
        }else if(user != null ){
            getNewMeals()
        }
        return emptyList()
    }



    override suspend fun getNewMeals(): List<RecipeResponse> {
        val userFlow = getAllUsersStream()
        val userF = userFlow.first()
        val user = userF.firstOrNull()
        if(user != null) {
            val retrievedNewMeals = retrieveMeals(user = user)
            val userCopiedPast = user.copy(pastRecipes = user.dailyRecipes)
            val finalUser = userCopiedPast.copy(dailyRecipes = retrievedNewMeals)
            userDao.update(finalUser)
            return retrievedNewMeals
        }
        return emptyList()
    }

    override suspend fun getRecipe(id: Int): RecipeResponse {
        val userFlow = getAllUsersStream()
        val userF = userFlow.first()
        val user = userF.firstOrNull()
        if(user != null) {

            //return the recipe if it is in the daily recipes
            user.dailyRecipes.forEach {
                if(it.id == id){
                    return it
                }
            }

            val mealInfo = getMealInfo(
                id = id,
                includeNutrition = true
            )
            mealInfo.body()?.let {

                val json = Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
                val mealInfo =
                    json.decodeFromString(RecipeResponse.serializer(), it.string())
                return mealInfo
            }
        }
        return RecipeResponse(0, "", 0, 0, "", "", "", Nutrition(emptyList()), "", emptyList())
    }

    override suspend fun deleteAllUsers() {
        val users = userDao.getAllItems().first()
        for (user in users) {
            deleteUser(user)
        }
    }

    override suspend fun checkIfUserExists(): Boolean {

        val userFlow = getAllUsersStream()

        val userF = userFlow.first()

        val user = userF.firstOrNull()

        if (user != null) {
            return true
        }
        return false
    }
}