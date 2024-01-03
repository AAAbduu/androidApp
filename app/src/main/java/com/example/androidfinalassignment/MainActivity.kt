package com.example.androidfinalassignment

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import com.example.androidfinalassignment.ui.theme.AndroidFinalAssignmentTheme
import com.example.androidfinalassignment.ui.onboard.OnBoardScreens
import com.example.androidfinalassignment.ui.signup.SignUpScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.androidfinalassignment.data.database.MyMealsDatabase
import com.example.androidfinalassignment.ui.detailedrecipeview.DetailedRecipeScreen
import com.example.androidfinalassignment.ui.detailedrecipeview.DetailedRecipeViewModel
import com.example.androidfinalassignment.ui.mainview.*
import com.example.androidfinalassignment.ui.mysplashscreen.ScreenState
import com.example.androidfinalassignment.ui.mysplashscreen.SplashScreen
import com.example.androidfinalassignment.ui.mysplashscreen.SplashScreenViewModel
import com.example.androidfinalassignment.ui.signup.SignUpViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        val splashScreenViewModel: SplashScreenViewModel by viewModels{
            ViewModelFactory(application)
        }
        splashScreen.setKeepOnScreenCondition{splashScreenViewModel.screenState.value == ScreenState.splashScreen}
        setContent {
            val navControllerUnregistered = rememberNavController()
            AndroidFinalAssignmentTheme {
                NavHost(navController = navControllerUnregistered, startDestination = "splash") {
                    composable("splash") {
                        SplashScreen(splashScreenViewModel = splashScreenViewModel, navController = navControllerUnregistered)
                    }

                    composable("onBoardScreens") {
                        OnBoardScreens(navController = navControllerUnregistered)
                    }
                    composable("signUpScreen") {
                        val signUpViewModel: SignUpViewModel = viewModel(factory = SignUpViewModel.Factory)
                        SignUpScreen(navController = navControllerUnregistered, signUpViewModel = signUpViewModel)
                    }
                    composable("mainViewScreen") {
                        val navControllerRegistered = rememberNavController()
                        val mainViewModelView: MainViewModelView = viewModel(factory = MainViewModelView.Factory)
                        MainViewScreenView(navControllerUnregistered = navControllerUnregistered,
                            navControllerRegistered = navControllerRegistered,
                            mainViewModelView = mainViewModelView)
                    }
                    composable("detailedRecipeScreen/{recipeId}", arguments = listOf(navArgument("recipeId") { type = NavType.IntType})) {
                        val detailedRecipeViewModel: DetailedRecipeViewModel = viewModel(factory = DetailedRecipeViewModel.Factory)
                        DetailedRecipeScreen(detailedRecipeViewModel = detailedRecipeViewModel, recipeId = it.arguments?.getInt("recipeId")!!)
                    }
                }
            }
        }
    }
    class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SplashScreenViewModel::class.java)) {
                val finalAssignmentApplication = application as FinalAssignmentApplication
                val centralRepository = finalAssignmentApplication.container.centralRepository
                return SplashScreenViewModel(centralRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}