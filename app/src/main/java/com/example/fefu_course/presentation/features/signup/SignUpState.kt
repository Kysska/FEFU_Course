package com.example.fefu_course.presentation.features.signup

import androidx.compose.runtime.Immutable

@Immutable
data class SignUpState(
    val login: String = "",
    val name: String = "",
    val password: String = "",
    val passwordRetry: String = "",
    val gender: Gender = Gender.Other,
    val isLoading: Boolean = false,
    val loginError: String? = null,
    val nameError: String? = null,
    val passwordError: String? = null,
    val passwordRetryError: String? = null
) {
    companion object {
        const val EMPTY_LOGIN = "Логин не может быть пустым"
        const val PASSWORD_TOO_SHORT = "Пароль должен быть не меньше 6 символов"
        const val PASSWORD_RETRY_ERROR = "Повторный пароль не совпадает"
        const val EMPTY_NAME = "Имя пользователя не может быть пустым"
    }
}

enum class Gender {
    Woman,
    Men,
    Other
}
