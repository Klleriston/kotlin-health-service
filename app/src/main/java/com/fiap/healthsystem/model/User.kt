package com.fiap.healthsystem.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val username: String,
    val password: String,
    val weight: Double,
    val height: Double,
    val exercisesRegularly: Boolean,
    val hasMobilityImpairment: Boolean
)
