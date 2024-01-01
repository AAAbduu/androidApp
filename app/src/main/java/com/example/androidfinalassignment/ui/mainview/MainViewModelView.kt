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

class MainViewModelView (/*private val mealsRepository: MealsRepository,*/ private val centralRepository: CentralRepository): ViewModel(){
    private val _uiState = MutableStateFlow(MainViewUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getMeals()
    }

    fun updateName(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }

    fun updateSelectedTab(selectedTab: MainViewTabs) {
        _uiState.value = _uiState.value.copy(selectedTab = selectedTab)
    }
    fun getMeals() {
     viewModelScope.launch {
         val mealsRetrieved = centralRepository.retrieveSavedUsersMealPlan()
         _uiState.value = _uiState.value.copy(meals = mealsRetrieved)
        }
    }

    fun refreshMeals() {
        viewModelScope.launch {
            val mealsRetrieved = centralRepository.getNewMeals()
            _uiState.value = _uiState.value.copy(meals = mealsRetrieved)
        }
    }

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