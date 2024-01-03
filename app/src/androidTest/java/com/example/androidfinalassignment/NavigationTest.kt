package com.example.androidfinalassignment

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.androidfinalassignment.domain.AnalyzedInstruction
import com.example.androidfinalassignment.domain.Equipment
import com.example.androidfinalassignment.domain.Ingredient
import com.example.androidfinalassignment.domain.InstructionStep
import com.example.androidfinalassignment.domain.Nutrient
import com.example.androidfinalassignment.domain.Nutrition
import com.example.androidfinalassignment.domain.RecipeResponse
import com.example.androidfinalassignment.ui.detailedrecipeview.DetailedRecipeScreen
import com.example.androidfinalassignment.ui.detailedrecipeview.DetailedRecipeViewModel
import com.example.androidfinalassignment.ui.mainview.MainViewModelView
import com.example.androidfinalassignment.ui.mainview.MainViewScreenView
import com.example.androidfinalassignment.ui.mainview.MainViewTopBar
import com.example.androidfinalassignment.ui.mainviewsearch.MainSearchViewBody
import com.example.androidfinalassignment.ui.mainviewsearch.MainViewSearchViewModel
import com.example.androidfinalassignment.ui.mysplashscreen.SplashScreen
import com.example.androidfinalassignment.ui.mysplashscreen.SplashScreenViewModel
import com.example.androidfinalassignment.ui.onboard.OnBoardScreens
import com.example.androidfinalassignment.ui.signup.SignUpScreen
import com.example.androidfinalassignment.ui.signup.SignUpViewModel
import okhttp3.internal.wait
import org.junit.Rule
import org.junit.Test

class NavigationTest {


    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    // Test that the onBoardScreens screen is displayed
    @Test
    fun testOnBoardScreensNavigation() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            OnBoardScreens(navController = navController)
        }
        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText("Get Started").assertIsDisplayed()
    }

    // Test that the signUpScreen screen is displayed
    @Test
    fun testSignUpScreenNavigation() {
        composeTestRule.setContent {
            val signUpViewModel: SignUpViewModel = viewModel(factory = SignUpViewModel.Factory)
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            SignUpScreen(navController = navController, signUpViewModel = signUpViewModel)
        }
        composeTestRule.onNodeWithText("Name").assertIsDisplayed()
    }

    /**
     * Test that the mainViewScreen screen is displayed
     */

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun testMainViewScreenNavigation() {
        composeTestRule.setContent {
            val navControllerRegistered = TestNavHostController(LocalContext.current)
            val navControllerUnregistered = TestNavHostController(LocalContext.current)
            val mainViewModelView: MainViewModelView = viewModel(factory = MainViewModelView.Factory)
            navControllerUnregistered.navigatorProvider.addNavigator(ComposeNavigator())
            navControllerRegistered.navigatorProvider.addNavigator(ComposeNavigator())
            MainViewScreenView(
                navControllerUnregistered = navControllerUnregistered,
                navControllerRegistered = navControllerRegistered,
                mainViewModelView = mainViewModelView
            )
        }
        composeTestRule
            .onNodeWithText("MyMeals").assertIsDisplayed()
    }
    /**
     * Test navigation to search screen
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun testSearchScreenNavigation() {
        composeTestRule.setContent {
            val navControllerUnregistered = TestNavHostController(LocalContext.current)
            val navControllerRegistered = TestNavHostController(LocalContext.current)
            val mainViewModelView: MainViewModelView = viewModel(factory = MainViewModelView.Factory)
            navControllerUnregistered.navigatorProvider.addNavigator(ComposeNavigator())
            navControllerRegistered.navigatorProvider.addNavigator(ComposeNavigator())
            MainViewScreenView(
                navControllerUnregistered = navControllerUnregistered,
                navControllerRegistered = navControllerRegistered,
                mainViewModelView = mainViewModelView
            )
        }
        composeTestRule
            .onNodeWithContentDescription("Search recipes").performClick()
        composeTestRule
            .onNodeWithContentDescription("Search Icon").assertIsDisplayed()
    }
}