package com.fiap.healthsystem.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.fiap.healthsystem.viewModel.UserViewModel

@Composable
fun MainScreen(viewModel: UserViewModel, userId: Int) {
    val user by viewModel.user.collectAsState()
    val temperature by viewModel.temperature.collectAsState()

    var waterIntake by remember { mutableStateOf(0) }

    // Chama a API de clima assim que a tela for exibida
    LaunchedEffect(Unit) {
        viewModel.fetchWeather()
    }

    Column {
        Text("Hello, ${user?.username ?: "User"} (ID: $userId)")

        // Exibe a temperatura diretamente se disponível, caso contrário, mostra "Buscando temperatura..."
        Text("Temperatura atual em SP: ${temperature?.let { "$it°C" } ?: "Buscando temperatura..."}")

        Text("Water Intake Today: $waterIntake ml")

        Button(onClick = { waterIntake += 250 }) {
            Text("Add 250ml")
        }
    }
}
