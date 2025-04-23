package com.example.fefu_course.presentation.features.signin

import androidx.compose.runtime.Immutable

@Immutable
data class SignInState(
    val login: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val loginError: String? = null,
    val passwordError: String? = null,
    val error: String? = null,
    val isSuccess: Boolean = false
) {
    companion object {
        const val EMPTY_LOGIN = "Логин не может быть пустым"
        const val PASSWORD_EMPTY = "Пароль не может быть пустым"
    }
}
