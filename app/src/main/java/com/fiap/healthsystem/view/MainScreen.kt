package com.fiap.healthsystem.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.fiap.healthsystem.viewModel.UserViewModel

@Composable
fun MainScreen(viewModel: UserViewModel, userId: Int) {
    val user by viewModel.user.collectAsState()

    var waterIntake by remember { mutableStateOf(0) }

    Column {
        Text("Hello, ${user?.username ?: "User"} (ID: $userId)")
        Text("Current Weather: 23°C") // Simulated for simplicity
        Text("Water Intake Today: $waterIntake ml")
        Button(onClick = { waterIntake += 250 }) {
            Text("Add 250ml")
        }
    }
}
