package com.example.fefu_course.presentation.navigation

import androidx.annotation.DrawableRes

sealed class RootScreen(val route: String, val label: String?, @DrawableRes val iconId: Int?) {
    data object Auth : RootScreen("auth_screen", null, null)
}

sealed class Screen(val route: String) {
    data object Welcome : Screen("welcome")
    data object SignUp : Screen("signUp")
    data object SignIn : Screen("signIn")
}
