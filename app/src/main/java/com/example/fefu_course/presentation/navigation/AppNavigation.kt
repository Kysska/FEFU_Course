package com.example.fefu_course.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.fefu_course.presentation.ui.screen.SignInScreen
import com.example.fefu_course.presentation.ui.screen.SignUpScreen
import com.example.fefu_course.presentation.ui.screen.WelcomeScreen

@Composable
fun AppNavigation(innerPaddingValues: PaddingValues) {
    val navController = rememberNavController()
    val isLoggedIn = false

    NavHost(
        navController = navController,
        startDestination = if (!isLoggedIn) RootScreen.Auth.route else RootScreen.Auth.route,
        modifier = Modifier.padding(innerPaddingValues)
    ) {
        addAuthRoot(navController)
    }
}

private fun NavGraphBuilder.addAuthRoot(navController: NavController) {
    navigation(
        route = RootScreen.Auth.route,
        startDestination = Screen.Welcome.route
    ) {
        addWelcome(navController)
        addSignup(navController)
        addSignin(navController)
    }
}

private fun NavGraphBuilder.addWelcome(navController: NavController) {
    composable(Screen.Welcome.route) {
        WelcomeScreen(navController = navController)
    }
}

private fun NavGraphBuilder.addSignup(navController: NavController) {
    composable(Screen.SignUp.route) {
        SignUpScreen(navController = navController)
    }
}

private fun NavGraphBuilder.addSignin(navController: NavController) {
    composable(Screen.SignIn.route) {
        SignInScreen(navController = navController)
    }
}
