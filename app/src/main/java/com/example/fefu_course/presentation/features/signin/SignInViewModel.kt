package com.example.fefu_course.presentation.features.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState> = _state.asStateFlow()

    fun onLoginChanged(login: String) {
        _state.update { it.copy(login = login, loginError = validateLogin(login)) }
    }

    fun onPasswordChanged(password: String) {
        _state.update { it.copy(password = password, passwordError = validatePassword(password)) }
    }

    fun signIn() {
        viewModelScope.launch {
            val currentState = _state.value

            if (validateLogin(currentState.login) != null || validatePassword(currentState.password) != null) {
                return@launch
            }

            _state.update { it.copy(isLoading = true, error = null, isSuccess = false) }

            try {
                _state.update { it.copy(isLoading = false, isSuccess = true) }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message ?: "Неизвестная ошибка") }
            }
        }
    }

    private fun validateLogin(login: String): String? {
        return if (login.isEmpty()) SignInState.EMPTY_LOGIN else null
    }

    private fun validatePassword(password: String): String? {
        return if (password.isEmpty()) SignInState.PASSWORD_EMPTY else null
    }
}
