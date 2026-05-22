package com.example.gradeflow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gradeflow.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val repository = AuthRepository()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _loading.value = true

            val result = repository.login(email, password)

            _loading.value = false

            result.onSuccess {
                onSuccess()
            }.onFailure {
                _error.value = it.message
            }
        }
    }

    fun register(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _loading.value = true

            val result = repository.register(email, password)

            _loading.value = false

            result.onSuccess {
                onSuccess()
            }.onFailure {
                _error.value = it.message
            }
        }
    }

    fun logout() {
        repository.logout()
    }

    fun isLoggedIn(): Boolean {
        return repository.isUserLoggedIn()
    }
}