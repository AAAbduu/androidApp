package com.example.androidfinalassignment.ui.mainview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidfinalassignment.FinalAssignmentApplication
import com.example.androidfinalassignment.data.CentralRepository
import com.example.androidfinalassignment.domain.Meal
import com.example.androidfinalassignment.domain.RecipeResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.io.IOException


/**
 * MainViewModelView is a view model that holds the state of the main view.
 * Also retrieves the data about meals from the repository.
 */
class MainViewModelView (/*private val mealsRepository: MealsRepository,*/ private val centralRepository: CentralRepository): ViewModel(){
    private val _uiState = MutableStateFlow(MainViewUiState())
    val uiState = _uiState.asStateFlow()


    init {
        getMeals()
    }

    /**
     * updateSelectedTab is a function that updates the selected tab.
     *
     * @param selectedTab is the tab that is selected.
     */
    fun updateSelectedTab(selectedTab: MainViewTabs) {
        _uiState.value = _uiState.value.copy(selectedTab = selectedTab)
    }

    /**
     * getMeals is a function that retrieves the meals from the repository.
     */
    fun getMeals() {
     viewModelScope.launch {
         val mealsRetrieved = centralRepository.retrieveSavedUsersMealPlan()
         _uiState.value = _uiState.value.copy(meals = mealsRetrieved)
        }
    }

    /*private fun deleteTable() {
        viewModelScope.launch {
            centralRepository.delete()
        }
    }*/

    /**
     * refreshMeals is a function that retrieves new meals from the repository.
     */
    fun refreshMeals() {
        viewModelScope.launch {
            val mealsRetrieved = centralRepository.getNewMeals()
            _uiState.value = _uiState.value.copy(meals = mealsRetrieved)
        }
    }

    /**
     * Companion object that creates the view model with parameters, otherwise cannot be created with them.
     */
    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FinalAssignmentApplication)
                //val myMealsRepository = application.container.mealRepository
                val centralRepository = application.container.centralRepository
                MainViewModelView(/*myMealsRepository,*/ centralRepository)
            }
        }
    }
}