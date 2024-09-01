package com.fiap.healthsystem.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import com.fiap.healthsystem.viewModel.UserViewModel

@Composable
fun LoginScreen(
    viewModel: UserViewModel,
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginError by remember { mutableStateOf(false) }

    Column {
        TextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
        TextField(value = password, onValueChange = { password = it }, label = { Text("Password") })

        if (loginError) {
            Text("Usuário ou senha inválidos", color = androidx.compose.ui.graphics.Color.Red)
        }

        Button(onClick = {
            viewModel.loginUser(username, password)
            if (viewModel.user.value != null) {
                loginError = false
                onLoginSuccess()
            } else {
                loginError = true
            }
        }) {
            Text("Login")
        }

        Button(onClick = onNavigateToRegister) {
            Text("Não tem uma conta? Registre-se")
        }
    }
}
