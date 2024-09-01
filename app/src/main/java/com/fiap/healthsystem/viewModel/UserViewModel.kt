package com.fiap.healthsystem.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiap.healthsystem.database.dao.UserDao
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.fiap.healthsystem.model.User

class UserViewModel(private val userDao: UserDao) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> get() = _user.asStateFlow()

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
}
