package com.example.fefu_course.presentation.features.signup.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.fefu_course.presentation.features.signup.SignUpScreen
import com.example.fefu_course.presentation.features.signup.SignUpViewModel
import com.example.fefu_course.presentation.navigation.AuthScreen
import com.example.fefu_course.presentation.navigation.BottomNavigationRoot
import com.example.fefu_course.presentation.navigation.Root

fun NavGraphBuilder.addSignup(navController: NavController) {
    composable(AuthScreen.SignUp.route) {
        val viewModel = hiltViewModel<SignUpViewModel>()
        val state by viewModel.state.collectAsState()
        SignUpScreen(signUpState = state,
            onLoginChanged = { viewModel.onLoginChanged(it) },
            onPasswordChanged = { viewModel.onPasswordChanged(it) },
            onNameChanged = { viewModel.onNameChanged(it) },
            onRetryPasswordChanged = { viewModel.onPasswordRetryChanged(it) },
            onGenderChanged = { viewModel.onGenderChanged(it) },
            onSignUp = { viewModel.signUp() },
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
