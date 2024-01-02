package com.example.androidfinalassignment

import android.app.Application
import androidx.compose.ui.platform.LocalContext
import com.example.androidfinalassignment.data.AppContainer
import com.example.androidfinalassignment.data.DefaultAppContainer


/**
 * The application class that provides the dependencies to the app.
 */
class FinalAssignmentApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}