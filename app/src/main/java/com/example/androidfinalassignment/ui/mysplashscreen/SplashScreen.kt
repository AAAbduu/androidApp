package com.example.androidfinalassignment.ui.mysplashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androidfinalassignment.R
import com.example.androidfinalassignment.ui.mainview.MainViewModelView
import com.example.androidfinalassignment.ui.mainview.MainViewScreenView
import com.example.androidfinalassignment.ui.onboard.OnBoardScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen(splashScreenViewModel: SplashScreenViewModel, navController: NavController) {
    val screenState by splashScreenViewModel.screenState.collectAsState()
    when (screenState) {
        ScreenState.onBoardScreens -> LaunchedEffect(Unit) { navController.navigate("onBoardScreens") }
        ScreenState.mainViewScreen -> LaunchedEffect(Unit) { navController.navigate("mainViewScreen") }
        else -> {}
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(painter = painterResource(id = R.drawable.logo_color), contentDescription = "Logo")
    }
}




@Composable
@Preview
fun SplashScreenPreview() {
    val splashScreenViewModel: SplashScreenViewModel = viewModel(factory = SplashScreenViewModel.Factory)

    val navController = rememberNavController()
    SplashScreen(splashScreenViewModel= splashScreenViewModel, navController = navController)
}