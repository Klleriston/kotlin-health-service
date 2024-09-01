package com.fiap.healthsystem.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import com.fiap.healthsystem.model.User
import com.fiap.healthsystem.viewModel.UserViewModel
@Composable
fun RegisterScreen(viewModel: UserViewModel, onRegisterSuccess: () -> Unit, onNavigateToLogin: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var exercisesRegularly by remember { mutableStateOf(false) }
    var hasMobilityImpairment by remember { mutableStateOf(false) }

    Column {
        TextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
        TextField(value = password, onValueChange = { password = it }, label = { Text("Password") })
        TextField(value = weight, onValueChange = { weight = it }, label = { Text("Weight") })
        TextField(value = height, onValueChange = { height = it }, label = { Text("Height") })
        Checkbox(checked = exercisesRegularly, onCheckedChange = { exercisesRegularly = it })
        Text("Exercises Regularly")
        Checkbox(checked = hasMobilityImpairment, onCheckedChange = { hasMobilityImpairment = it })
        Text("Has Mobility Impairment")
        Button(onClick = {
            viewModel.registerUser(
                User(
                    username = username,
                    password = password,
                    weight = weight.toDouble(),
                    height = height.toDouble(),
                    exercisesRegularly = exercisesRegularly,
                    hasMobilityImpairment = hasMobilityImpairment
                )
            )
            onRegisterSuccess()
            onNavigateToLogin()
        }) {
            Text("Register")
        }
    }
}
