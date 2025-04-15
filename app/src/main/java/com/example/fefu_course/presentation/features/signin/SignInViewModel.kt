package com.example.fefu_course.presentation.features.signin

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

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

    fun signIn(state: SignInState): Boolean {
        return validateLogin(state.login) == null && validatePassword(state.password) == null
    }

    private fun validateLogin(login: String): String? {
        return if (login.isEmpty()) SignInState.EMPTY_LOGIN else null
    }

    private fun validatePassword(password: String): String? {
        return if (password.isEmpty()) SignInState.PASSWORD_EMPTY else null
    }
}
