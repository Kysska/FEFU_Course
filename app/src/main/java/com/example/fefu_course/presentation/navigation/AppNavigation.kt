package com.example.fefu_course.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.fefu_course.presentation.ui.screen.main.ActivityScreen
import com.example.fefu_course.presentation.ui.screen.main.MyActivity
import com.example.fefu_course.presentation.ui.screen.main.ProfileScreen
import com.example.fefu_course.presentation.ui.screen.main.UserActivity
import com.example.fefu_course.presentation.ui.screen.main.UserScreen
import com.example.fefu_course.presentation.ui.widget.BottomNavigationBar

@Composable
fun AppNavigation(innerPaddingValues: PaddingValues) {
    val navController = rememberNavController()
    val isLoggedIn = false

    val showBottomBar by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = if (!isLoggedIn) Root.Auth.route else BottomNavigationRoot.Activity.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            addAuthRoot(navController)
            addActivityRoot(navController)
            addUserRoot(navController)
        }
    }
}

private fun NavGraphBuilder.addAuthRoot(navController: NavController) {
    navigation(
        route = Root.Auth.route,
        startDestination = AuthScreen.Welcome.route
    ) {
        addWelcome(navController)
        addSignup(navController)
        addSignin(navController)
    }
}

fun NavGraphBuilder.addActivityRoot(navController: NavController) {
    navigation(
        route = BottomNavigationRoot.Activity.route,
        startDestination = MainScreen.ActivityScreen.route
    ) {
        addActivityScreen(navController)
    }
}

fun NavGraphBuilder.addUserRoot(navController: NavController) {
    navigation(
        route = BottomNavigationRoot.User.route,
        startDestination = MainScreen.UserScreen.route
    ) {
        addUserScreen(navController)
    }
}

private fun NavGraphBuilder.addWelcome(navController: NavController) {
    composable(AuthScreen.Welcome.route) {
        WelcomeScreen(navController = navController)
    }
}

private fun NavGraphBuilder.addSignup(navController: NavController) {
    composable(AuthScreen.SignUp.route) {
        SignUpScreen(navController = navController)
    }
}

private fun NavGraphBuilder.addSignin(navController: NavController) {
    composable(AuthScreen.SignIn.route) {
        SignInScreen(navController = navController)
    }
}

fun NavGraphBuilder.addUserScreen(navController: NavController) {
    composable(MainScreen.UserScreen.route) {
        UserScreen(navController)
    }
}

private fun NavGraphBuilder.addActivityScreen(navController: NavController) {
    composable(MainScreen.ActivityScreen.route) {
        ActivityScreen(navController)
    }
}

fun NavGraphBuilder.addMyActivityScreen() {
    composable(ActivityScreen.MyActivity.route) {
        MyActivity()
    }
}

fun NavGraphBuilder.addUserActivityScreen() {
    composable(ActivityScreen.UserActivity.route) {
        UserActivity()
    }
}

fun NavGraphBuilder.addProfileScreen() {
    composable(UserScreen.ProfileScreen.route) {
        ProfileScreen()
    }
}
