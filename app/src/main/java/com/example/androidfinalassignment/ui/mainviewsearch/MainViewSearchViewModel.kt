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


/**
 * MainViewSearchViewModel is the ViewModel of the MainViewSearchScreen.
 */
class MainViewSearchViewModel(/*rivate val mealsRepository: MealsRepository,*/ private val centralRepository: CentralRepository) : ViewModel(){

    private val _uiState = MutableStateFlow(MainViewSearchUiState())
    val uiState = _uiState.asStateFlow()

    /**
     * Updates the query in the uiState and calls findMeals().
     */
    fun updateSearchQuery(searchQuery: String) {
        _uiState.value = _uiState.value.copy(query = searchQuery)
        findMeals()
    }

    /**
     * Updates the foundMeals in the uiState.
     */
    fun updateFoundMeals(foundMeals: List<RecipeResponse>) {
        _uiState.value = _uiState.value.copy(foundMeals = foundMeals)
    }

    /**
     * Finds meals based on the query in the uiState.
     */
    private fun findMeals() {
        viewModelScope.launch {
            var mealsRetrieved = listOf<RecipeResponse>()

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

                        mealsRetrieved += mealInfo
                    }

                }
            } catch (e: Exception) {

                println(e)
            }
            updateFoundMeals(mealsRetrieved)
        }
    }

    /**
     * Factory for creating a MainViewSearchViewModel with a constructor that takes a [MealsRepository] and a [CentralRepository].
     */
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