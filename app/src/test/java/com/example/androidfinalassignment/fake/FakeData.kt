package com.example.androidfinalassignment.fake

import com.example.androidfinalassignment.data.User
import com.example.androidfinalassignment.domain.MealSerial
import com.example.androidfinalassignment.domain.MealsResponse
import com.example.androidfinalassignment.domain.Nutrition
import com.example.androidfinalassignment.domain.RecipeResponse
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object FakeData {

    var user = User(
        id = 1,
        name = "Test",
        weight = 70f,
        height = 170f,
        age = "1",
        gender = "male",
        dailyCalories = 2000.0,
        foodAllergies = "test",
        cuisineDislikes = "test",
        dietPreference = "test",
        dailyRecipes = emptyList(),
        pastRecipes = emptyList()
    )

    var recipeResponse1 = RecipeResponse(
        id = 1,
        title = "Test",
        readyInMinutes = 1,
        servings = 1,
        sourceUrl = "test",
        image = "test",
        imageType = "test",
        nutrition = Nutrition(
            nutrients = emptyList()
        ),
        instructions = "test",
        analyzedInstructions = emptyList()
    )

    val recipeResponse1Json = """
    {
      "id": 1,
      "title": "Test",
      "readyInMinutes": 1,
      "servings": 1,
      "sourceUrl": "test",
      "image": "test",
      "imageType": "test",
      "nutrition": {
        "nutrients": []
      },
      "instructions": "test",
      "analyzedInstructions": []
    }
""".trimIndent()

    var recipeResponse2 = RecipeResponse(
        id = 2,
        title = "Test",
        readyInMinutes = 1,
        servings = 1,
        sourceUrl = "test",
        image = "test",
        imageType = "test",
        nutrition = Nutrition(
            nutrients = emptyList()
        ),
        instructions = "test",
        analyzedInstructions = emptyList()
    )

    var recipeResponse3 = RecipeResponse(
        id = 3,
        title = "Test",
        readyInMinutes = 1,
        servings = 1,
        sourceUrl = "test",
        image = "test",
        imageType = "test",
        nutrition = Nutrition(
            nutrients = emptyList()
        ),
        instructions = "test",
        analyzedInstructions = emptyList()
    )

    var mealSerial1 = MealSerial(
        id = 1,
        title = "Test",
        imageType = "test",
        readyInMinutes = 1,
        servings = 1,
        sourceUrl = "test"
    )

    var mealSerial2 = MealSerial(
        id = 2,
        title = "Test",
        imageType = "test",
        readyInMinutes = 1,
        servings = 1,
        sourceUrl = "test"
    )

    var mealSerial3 = MealSerial(
        id = 3,
        title = "Test",
        imageType = "test",
        readyInMinutes = 1,
        servings = 1,
        sourceUrl = "test"
    )

    var mealSerialList = listOf(mealSerial1, mealSerial2, mealSerial3)

    var mealResponse1 = MealsResponse(
        meals = mealSerialList,
        nutrients = com.example.androidfinalassignment.domain.Nutrients(
            calories = 1.0,
            protein = 1.0,
            fat = 1.0,
            carbohydrates = 1.0
        )
    )


}