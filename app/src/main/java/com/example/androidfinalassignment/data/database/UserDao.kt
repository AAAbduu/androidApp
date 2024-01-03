package com.example.androidfinalassignment.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.androidfinalassignment.data.User
import kotlinx.coroutines.flow.Flow


/**
 * A data access object for the user table.
 */
@Dao
interface UserDao {

    /**
     * Insert a user in the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    /**
     * Update a user in the database.
     */
    @Update
    suspend fun update(user: User)

    /**
     * Delete a user in the database.
     */
    @Delete
    suspend fun delete(user: User)

    /**
     * Retrieve a user with specified id from the database.
     * @param id The id of the user.
     */
    @Query("SELECT * FROM user_table WHERE id = :id")
    fun getUser(id: Int): Flow<User>

    /**
     * Retrieve all users from the database.
     */
    @Query("SELECT * from user_table ORDER BY name ASC")
    fun getAllItems(): Flow<List<User>>

    /**
     * Delete the table in the database.
     */
    @Query("DELETE FROM user_table")
    fun deleteTable()

}