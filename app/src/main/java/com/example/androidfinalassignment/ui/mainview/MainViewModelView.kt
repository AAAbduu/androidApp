package com.example.androidfinalassignment.ui.mainview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidfinalassignment.FinalAssignmentApplication
import com.example.androidfinalassignment.data.MyMealsRepository
import com.example.androidfinalassignment.domain.Meal
import com.example.androidfinalassignment.domain.RecipeResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.io.IOException

class MainViewModelView (private val myMealsRepository: MyMealsRepository): ViewModel(){
    private val _uiState = MutableStateFlow(MainViewUiState())
    val uiState = _uiState.asStateFlow()

    fun updateName(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }

    fun updateSelectedTab(selectedTab: MainViewTabs) {
        _uiState.value = _uiState.value.copy(selectedTab = selectedTab)
    }
      fun getMeals() {
         viewModelScope.launch {
             var mealsRetrieved = listOf<Meal>()
             try {
                 val response = myMealsRepository.getMealsDay(
                     timeFrame = "day",
                     targetCalories = 2000,
                     offset = "vegetarian",
                     exclude = "shellfish"
                 )
                 for (meal in response.meals) {
                        val mealInfo = myMealsRepository.getMealInfo(
                            id = meal.id,
                            includeNutrition = true
                        )
                        println(mealInfo)
                     mealInfo.body()?.let {
                         val json = Json { ignoreUnknownKeys = true
                             isLenient = true }
                            val recipeResponse = json.decodeFromString(RecipeResponse.serializer(), it.string())
                         val newMeal = Meal(
                             id = meal.id,
                             title = meal.title,
                             imageType = meal.imageType,
                             readyInMinutes = meal.readyInMinutes,
                             servings = meal.servings,
                             sourceUrl = meal.sourceUrl,
                         )
                            mealsRetrieved += newMeal
                     }
                 }
             } catch (e: IOException) {
                 e.printStackTrace()
             }
                _uiState.value = _uiState.value.copy(meals = mealsRetrieved)
         }
     }
    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FinalAssignmentApplication)
                val myMealsRepository = application.container.mealRepository
                MainViewModelView(myMealsRepository)
            }
        }
    }
}