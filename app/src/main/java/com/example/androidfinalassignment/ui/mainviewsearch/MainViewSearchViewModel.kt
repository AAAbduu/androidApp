package com.example.androidfinalassignment.ui.mainviewsearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidfinalassignment.FinalAssignmentApplication
import com.example.androidfinalassignment.data.CentralRepository
import com.example.androidfinalassignment.domain.AutocompleteMeal
import com.example.androidfinalassignment.domain.Meal
import com.example.androidfinalassignment.domain.RecipeResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class MainViewSearchViewModel(/*rivate val mealsRepository: MealsRepository,*/ private val centralRepository: CentralRepository) : ViewModel(){

    private val _uiState = MutableStateFlow(MainViewSearchUiState())
    val uiState = _uiState.asStateFlow()


    fun updateSearchQuery(searchQuery: String) {
        _uiState.value = _uiState.value.copy(query = searchQuery)
        findMeals()
    }

    fun updateFoundMeals(foundMeals: List<Meal>) {
        _uiState.value = _uiState.value.copy(foundMeals = foundMeals)
    }

    private fun findMeals() {
        viewModelScope.launch {
            var mealsRetrieved = listOf<Meal>()

            try {
                val response = centralRepository.getAutocompletedTitleRecipe(
                    query = uiState.value.query,
                    number = 1
                )

                print(response)

                val responseList: List<AutocompleteMeal> = response.body()!!.string().let {
                    val json = Json { ignoreUnknownKeys = true
                        isLenient = true }
                    json.decodeFromString(ListSerializer(AutocompleteMeal.serializer()), it)
                }

                for (meal in responseList) {
                    val mealInfo = centralRepository.getMealInfo(
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
                        val newMeal = Meal(
                            id = meal.id,
                            title = meal.title,
                            imageType = meal.imageType,
                            readyInMinutes = 60,
                            servings = 0,
                            sourceUrl = "",
                            image = mealInfo.image
                        )
                        mealsRetrieved += newMeal
                    }

                }
            } catch (e: Exception) {

                println(e)
            }
            updateFoundMeals(mealsRetrieved)
        }
    }

    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer{
                val application = (this[APPLICATION_KEY] as FinalAssignmentApplication)
                //val myMealsRepository = application.container.mealRepository
                val centralRepository = application.container.centralRepository
                MainViewSearchViewModel(/*myMealsRepository,*/ centralRepository)
            }
        }
    }

}