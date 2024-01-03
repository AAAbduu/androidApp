package com.example.androidfinalassignment

import com.example.androidfinalassignment.domain.RecipeResponse
import com.example.androidfinalassignment.fake.FakeData
import com.example.androidfinalassignment.fake.FakeMyMealsApi
import com.example.androidfinalassignment.fake.FakeMyMealsApiRepository
import com.example.androidfinalassignment.fake.JsonHelper
import kotlinx.coroutines.flow.first
import org.junit.Test
import kotlinx.coroutines.test.runTest


import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MyMealsRepositoryTest {

    private lateinit var fakeMyMealsApi: FakeMyMealsApi
    private lateinit var fakeMyMealsApiRepository: FakeMyMealsApiRepository



    val json = JsonHelper()


    @Before
    fun setUp() {
        fakeMyMealsApi = FakeMyMealsApi()
        fakeMyMealsApiRepository = FakeMyMealsApiRepository()
    }

    @Test
    fun testGetMealsDayIsReceivedAndSaved() = runTest {
        val mealsResponse = fakeMyMealsApi.getMealsDay("day", 2000, "0", "")
        val recipeResponse = fakeMyMealsApi.getMealInfo(mealsResponse.meals[0].id, true)

        val recipeResponseBody = recipeResponse.body()?.string()

        println(recipeResponseBody)

        val recipeResponse1 = json.getObjectFromString(recipeResponseBody!!)

        FakeData.user.dailyRecipes = listOf(recipeResponse1) as List<RecipeResponse>

        fakeMyMealsApiRepository.insertUser(FakeData.user)
        val user = fakeMyMealsApiRepository.getUser(1)

        assertEquals(user.first().dailyRecipes, listOf(recipeResponse1))
    }

}