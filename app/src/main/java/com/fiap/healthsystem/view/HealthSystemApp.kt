package com.fiap.healthsystem.view

import androidx.compose.runtime.*
import com.fiap.healthsystem.viewModel.UserViewModel

@Composable
fun HealthSystemApp(userViewModel: UserViewModel) {
    var currentScreen by remember { mutableStateOf("login") }
    var userId by remember { mutableStateOf<Int?>(null) }

    when (currentScreen) {
        "login" -> LoginScreen(
            viewModel = userViewModel,
            onLoginSuccess = {
                userId = userViewModel.user.value?.id
                currentScreen = "main"
            },
            onNavigateToRegister = { currentScreen = "register" }
        )
        "register" -> RegisterScreen(userViewModel) {
            userId = userViewModel.user.value?.id // Capture the new user's ID after registration
            currentScreen = "main"
        }
        "main" -> userId?.let { MainScreen(userViewModel, userId = it) }
    }
}
