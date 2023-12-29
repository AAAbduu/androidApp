package com.example.androidfinalassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.androidfinalassignment.ui.theme.AndroidFinalAssignmentTheme
import com.example.androidfinalassignment.views.onboard.OnBoardScreens
import com.example.androidfinalassignment.views.signup.SignUpScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        val whatScreen = false
        super.onCreate(savedInstanceState)
        setContent {
            AndroidFinalAssignmentTheme {
                if (whatScreen) {
                    OnBoardScreens()
                } else {
                    SignUpScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidFinalAssignmentTheme {
        OnBoardScreens()
    }
}