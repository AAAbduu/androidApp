package com.example.androidfinalassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidfinalassignment.ui.theme.AndroidFinalAssignmentTheme
import com.example.androidfinalassignment.ui.onboard.OnBoardScreens
import com.example.androidfinalassignment.ui.signup.SignUpScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidfinalassignment.ui.mainview.*
import com.example.androidfinalassignment.ui.signup.SignUpViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            val navControllerUnregistered = rememberNavController()
            AndroidFinalAssignmentTheme {
                NavHost(navController = navControllerUnregistered, startDestination = "mainViewScreen") {
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
                        MainViewScreenView(navController = navControllerRegistered,
                            mainViewModelView = mainViewModelView)
                    }
                }
            }
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