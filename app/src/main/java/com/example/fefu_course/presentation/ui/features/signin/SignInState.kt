package com.example.fefu_course.presentation.ui.features.signin

import androidx.compose.runtime.Immutable

@Immutable
data class SignInState(
    val login: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val loginError: String? = null,
    val passwordError: String? = null
) {
    companion object {
        const val EMPTY_LOGIN = "Логин не может быть пустым"
        const val PASSWORD_TOO_SHORT = "Пароль должен быть не меньше 6 символов"
    }
}
