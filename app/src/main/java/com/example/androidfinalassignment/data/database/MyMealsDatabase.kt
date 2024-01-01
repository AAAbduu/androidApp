package com.example.androidfinalassignment.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.androidfinalassignment.data.User

@Database(entities = [User::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MyMealsDatabase: RoomDatabase(){
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var Instance: MyMealsDatabase? = null

        fun getDatabase(context: Context): MyMealsDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MyMealsDatabase::class.java, "user_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }

}