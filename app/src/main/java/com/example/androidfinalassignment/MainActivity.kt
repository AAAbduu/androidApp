package com.example.androidfinalassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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
import com.example.androidfinalassignment.ui.signup.SignUpViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            val navControllerUnregistered = rememberNavController()
            //check whether user exists in database and navigate to appropriate screen
            LaunchedEffect (key1 = Unit){
                val userExists = withContext(Dispatchers.Main) {
                checkIfUserExists()
                }
                navControllerUnregistered.navigate(getStartDestination(userExists)){
                    popUpTo("onBoardScreens"){
                        inclusive = false
                    }
                }
            }
            AndroidFinalAssignmentTheme {
                NavHost(navController = navControllerUnregistered, startDestination = "onBoardScreens") {
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
    suspend private fun checkIfUserExists(): Boolean {
        val userFlow = MyMealsDatabase.getDatabase(this).userDao().getAllItems()
        val users = userFlow.firstOrNull()
        if (users != null) {
            return users.firstOrNull() != null
        }
        return false
    }

    private fun getStartDestination(userExists: Boolean): String {
        return if (userExists) {
            "mainViewScreen"
        } else {
            "onBoardScreens"
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidFinalAssignmentTheme {
        val navController = rememberNavController()
        OnBoardScreens(navController = navController)
    }
}