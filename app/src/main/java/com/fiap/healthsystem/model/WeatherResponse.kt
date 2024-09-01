package com.fiap.healthsystem.model

data class WeatherResponse(
    val main: Main
)

data class Main(
    val temp: Double
)