package com.example.fefu_course.presentation.features.signin.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.fefu_course.presentation.features.signin.SignInScreen
import com.example.fefu_course.presentation.features.signin.SignInViewModel
import com.example.fefu_course.presentation.navigation.AuthScreen
import com.example.fefu_course.presentation.navigation.BottomNavigationRoot
import com.example.fefu_course.presentation.navigation.Root

fun NavGraphBuilder.addSignin(navController: NavController) {
    composable(AuthScreen.SignIn.route) {
        val viewModel = hiltViewModel<SignInViewModel>()
        val state by viewModel.state.collectAsState()

        SignInScreen(state = state,
            onLoginChanged = { viewModel.onLoginChanged(it) },
            onPasswordChanged = { viewModel.onPasswordChanged(it) },
            onSignIn = { viewModel.signIn() },
            onNavigateToMainRoot = {
                navController.navigate(BottomNavigationRoot.Activity.route) {
                    popUpTo(Root.Auth.route) { inclusive = true }
                    launchSingleTop = true
                }
            },
            onBackNavigate = {
                navController.navigateUp()
            }
        )
    }
}
