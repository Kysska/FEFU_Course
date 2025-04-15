package com.example.fefu_course.presentation.features.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState> = _state.asStateFlow()

    fun onLoginChanged(login: String) {
        _state.update { it.copy(login = login, loginError = validateLogin(login)) }
    }

    fun onNameChanged(name: String) {
        _state.update { it.copy(name = name, nameError = validateName(name)) }
    }

    fun onPasswordChanged(password: String) {
        _state.update { it.copy(password = password, passwordError = validatePassword(password)) }
    }

    fun onPasswordRetryChanged(password: String) {
        _state.update {
            it.copy(
                passwordRetry = password,
                passwordRetryError = validatePasswordRetry(password)
            )
        }
    }

    fun onGenderChanged(gender: Gender) {
        _state.update { it.copy(gender = gender) }
    }

    fun signUp(state: SignUpState): Boolean {
        Log.d("state", state.toString())
        return validateLogin(state.login) == null && validateName(state.name) == null &&
                validatePassword(state.password) == null && validatePasswordRetry(state.passwordRetry) == null
    }

    private fun validateLogin(login: String): String? {
        return if (login.isEmpty()) SignUpState.EMPTY_LOGIN else null
    }

    private fun validateName(name: String): String? {
        return if (name.isEmpty()) SignUpState.EMPTY_NAME else null
    }

    private fun validatePassword(password: String): String? {
        return if (password.length < 6) SignUpState.PASSWORD_TOO_SHORT else null
    }

    private fun validatePasswordRetry(password: String): String? {
        return if (password != state.value.password) SignUpState.PASSWORD_RETRY_ERROR else null
    }
}
