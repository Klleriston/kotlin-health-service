package com.fiap.healthsystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fiap.healthsystem.view.HealthSystemApp
import com.fiap.healthsystem.viewModel.UserViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fiap.healthsystem.viewModel.UserViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userDao = (application as HealthApp).database.userDao()

        setContent {
            val userViewModel: UserViewModel = viewModel(factory = UserViewModelFactory(userDao))
            HealthSystemApp(userViewModel)
        }
    }
}
