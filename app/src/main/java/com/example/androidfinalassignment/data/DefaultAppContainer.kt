package com.example.androidfinalassignment.data

import com.example.androidfinalassignment.data.database.MyMealsDatabase
import com.example.androidfinalassignment.network.MealsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import android.content.Context
import com.example.androidfinalassignment.network.RequestInterceptor
import okhttp3.OkHttpClient


/**
 * A class that provides the dependencies to the app.
 */
class DefaultAppContainer (private val context: Context): AppContainer {
    private val BASE_URL =
        "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com"

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor("X-RapidAPI-Key"))
            .build()
    }

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: MealsApiService by lazy {
        retrofit.create(MealsApiService::class.java)
    }

    override val centralRepository: CentralRepository by lazy {
        CentralUserMealsRepository(MyMealsDatabase.getDatabase(context).userDao(), retrofitService, context)
    }
}