package com.fiap.healthsystem.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiap.healthsystem.database.dao.UserDao
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.fiap.healthsystem.model.User
import com.fiap.healthsystem.service.RetrofitInstance
import retrofit2.await

class UserViewModel(private val userDao: UserDao) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> get() = _user.asStateFlow()

    private val _temperature = MutableStateFlow<Double?>(null)
    val temperature: StateFlow<Double?> get() = _temperature.asStateFlow()

    fun registerUser(user: User) {
        viewModelScope.launch {
            userDao.insertUser(user)
            _user.value = userDao.getUser(user.username, user.password)
        }
    }

    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            _user.value = userDao.getUser(username, password)
        }
    }

    fun fetchWeather() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getCurrentWeather("São Paulo", "2e5f8e562a18f9ea7bd09eae6f340b9a").await()
                _temperature.value = response.main.temp
            } catch (e: Exception) {
                _temperature.value = null
            }
        }
    }
}