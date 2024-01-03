package com.example.androidfinalassignment

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.androidfinalassignment.data.User
import com.example.androidfinalassignment.data.database.MyMealsDatabase
import com.example.androidfinalassignment.data.database.UserDao
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class UserDaoTest {
    private lateinit var database: MyMealsDatabase
    private lateinit var userDao: UserDao

    private var userTest = User (
        id = 1,
        name = "Test",
        weight = 1.0f,
        height = 1.0f,
        age = "1",
        gender = "male",
        dailyCalories = 1.0,
        foodAllergies = "test",
        cuisineDislikes = "test",
        dietPreference = "test",
        dailyRecipes = emptyList(),
        pastRecipes = emptyList()
    )

    @Before
    fun setupDB() {
        val context : Context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, MyMealsDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        userDao = database.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDD() {
        database.close()
    }

    // Test that the user is inserted into the database
    @Test
    @Throws(Exception::class)
    fun insertUser() = runBlocking{
        userDao.insertUser(userTest)
        val user = userDao.getUser(userTest.id)
        assert(user.firstOrNull()?.name == "Test")
    }

    // Test that the user is deleted from the database
    @Test
    @Throws(Exception::class)
    fun deleteUser() = runBlocking{
        userDao.insertUser(userTest)
        userDao.delete(userTest)
        val user = userDao.getUser(userTest.id)
        assert(user.firstOrNull() == null)
    }

    // Test that there is 1 user in the database
    @Test
    @Throws(Exception::class)
    fun countUser() = runBlocking{
        userDao.insertUser(userTest)
        val allUsers = userDao.getAllItems()
        val users = allUsers.firstOrNull()
        assertEquals(users?.size, 1)
    }

    // Test that the user is updated in the database
    @Test
    @Throws(Exception::class)
    fun updateUser() = runBlocking{
        userDao.insertUser(userTest)
        var userF = userDao.getUser(userTest.id)
        var user = userF.firstOrNull()
        user?.name = "Test2"
        userDao.update(user!!)
        val user2 = userDao.getUser(userTest.id)
        assert(user2.firstOrNull()?.name == "Test2")
    }

    //delete the table in the database
    @Test
    @Throws(Exception::class)
    fun deleteTable() = runBlocking{
        userDao.insertUser(userTest)
        userDao.delete()
        val allUsers = userDao.getAllItems()
        val users = allUsers.firstOrNull()
        assertEquals(users?.size, 0)
    }
}