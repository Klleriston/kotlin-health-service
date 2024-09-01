package com.fiap.healthsystem

import android.app.Application
import androidx.room.Room
import com.fiap.healthsystem.database.AppDatabase

class HealthApp : Application() {

    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "health-database"
        ).build()
    }
}
