package com.example.androidfinalassignment

import android.app.Application
import com.example.androidfinalassignment.data.AppContainer
import com.example.androidfinalassignment.data.DefaultAppContainer

class FinalAssignmentApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}