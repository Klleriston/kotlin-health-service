package com.fiap.healthsystem.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fiap.healthsystem.database.dao.UserDao
import com.fiap.healthsystem.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
