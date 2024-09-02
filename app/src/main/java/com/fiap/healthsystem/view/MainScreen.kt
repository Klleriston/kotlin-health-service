package com.fiap.healthsystem.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.fiap.healthsystem.viewModel.UserViewModel

@Composable
fun MainScreen(viewModel: UserViewModel, userId: Int) {
    val user by viewModel.user.collectAsState()
    val temperature by viewModel.temperature.collectAsState()
    var waterIntake by remember { mutableStateOf(0) }
    val dailyGoal = 3000
    val intakePercentage = (waterIntake.toFloat() / dailyGoal) * 100

    LaunchedEffect(Unit) {
        viewModel.fetchWeather()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bem-vindo, ${user?.username ?: "Usuário"} (ID: $userId)",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Temperatura atual em SP: ${temperature?.let { "$it°C" } ?: "Buscando temperatura..."}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        CircularProgressIndicator(
            progress = intakePercentage / 100,
            modifier = Modifier.size(120.dp),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 10.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "${waterIntake}ml de ${dailyGoal}ml",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { waterIntake += 250 },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Adicionar 250ml", color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        var customAmount by remember { mutableStateOf("") }

        OutlinedTextField(
            value = customAmount,
            onValueChange = { customAmount = it },
            label = { Text("Adicionar outra quantidade (ml)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                customAmount.toIntOrNull()?.let {
                    waterIntake += it
                    customAmount = ""
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Adicionar", color = Color.White)
        }
    }
}
