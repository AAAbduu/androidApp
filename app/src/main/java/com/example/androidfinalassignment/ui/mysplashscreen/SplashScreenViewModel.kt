package com.example.androidfinalassignment.ui.mysplashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidfinalassignment.FinalAssignmentApplication
import com.example.androidfinalassignment.data.CentralRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashScreenViewModel(private val centralRepository: CentralRepository ) : ViewModel() {
    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.splashScreen)
    val screenState: StateFlow<ScreenState> = _screenState

    init {
        decideNavigation()
    }

    private fun decideNavigation() {
        viewModelScope.launch {
            val valueFromDb = centralRepository.checkIfUserExists()
            if (valueFromDb) {
                _screenState.value = ScreenState.mainViewScreen
            } else {
                _screenState.value = ScreenState.onBoardScreens
            }
        }
    }

    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FinalAssignmentApplication)
                //val myMealsRepository = application.container.mealRepository
                val centralRepository = application.container.centralRepository
                SplashScreenViewModel(/*myMealsRepository,*/ centralRepository)
            }
        }
    }
}

enum class ScreenState {
    splashScreen , onBoardScreens, mainViewScreen
}