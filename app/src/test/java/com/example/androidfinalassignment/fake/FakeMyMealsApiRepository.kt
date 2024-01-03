package com.example.androidfinalassignment.fake

import com.example.androidfinalassignment.data.User
import com.example.androidfinalassignment.data.database.UserDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeMyMealsApiRepository : UserDao {

    private val users = mutableListOf<User>()
    override suspend fun insertUser(user: User) {
        users.add(user)
    }

    override suspend fun update(user: User) {
        val index = users.indexOfFirst { it.id == user.id }
        users[index] = user
    }

    override suspend fun delete(user: User) {
        users.remove(user)
    }

    override fun deleteTable() {
        //DO nothing
    }

    override fun getUser(id: Int): Flow<User> {
        return flowOf(users.first { it.id == id })
    }

    override fun getAllItems(): Flow<List<User>> {
        return flowOf(users)
    }
}