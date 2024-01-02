package com.example.androidfinalassignment.ui.detailedrecipeview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidfinalassignment.FinalAssignmentApplication
import com.example.androidfinalassignment.data.CentralRepository
import com.example.androidfinalassignment.ui.mainview.MainViewModelView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * DetailedRecipeViewModel is a view model that holds the state of the detailed recipe view.
 *
 * @param centralRepository is the repository that holds the data of the application.
 */
class DetailedRecipeViewModel (private val centralRepository: CentralRepository): ViewModel(){
    private val _uiState = MutableStateFlow(DetailedRecipeUiState())
    val uiState = _uiState.asStateFlow()


    /**
     * getRecipe is a function that retrieves the recipe from the repository.
     *
     * @param id is the id of the recipe to be retrieved.
     */
    fun getRecipe(id: Int) {
        viewModelScope.launch {
            val recipeRetrieved = centralRepository.getRecipe(id)
            _uiState.value = _uiState.value.copy(name = recipeRetrieved.title)

            val ingredients = ArrayList<String>()

            recipeRetrieved.analyzedInstructions.forEach { instruction ->
                instruction.steps.forEach { step ->
                    step.ingredients.forEach { ingredient ->
                        ingredients += ingredient.name
                    }
                }
            }

            _uiState.value = _uiState.value.copy(ingredients = ingredients)
            _uiState.value = _uiState.value.copy(instructions = recipeRetrieved.instructions.replace(Regex("<[^>]*>"), "").replace(Regex("\\s+"), " "))
            _uiState.value = _uiState.value.copy(image = recipeRetrieved.image)

            val equipment = ArrayList<String>()

            recipeRetrieved.analyzedInstructions.forEach { instruction ->
                instruction.steps.forEach { step ->
                    step.equipment.forEach { equipmentItem ->
                        equipment += equipmentItem.name
                    }
                }
            }
            _uiState.value = _uiState.value.copy(equipment = equipment)
        }
    }


    /**
     * Factory is a companion object that creates the DetailedRecipeViewModel.
     */
    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FinalAssignmentApplication)
                //val myMealsRepository = application.container.mealRepository
                val centralRepository = application.container.centralRepository
                DetailedRecipeViewModel(/*myMealsRepository,*/ centralRepository)
            }
        }
    }

}