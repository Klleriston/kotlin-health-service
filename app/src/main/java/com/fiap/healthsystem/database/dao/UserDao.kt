package com.fiap.healthsystem.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fiap.healthsystem.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    suspend fun getUser(username: String, password: String): User?
}