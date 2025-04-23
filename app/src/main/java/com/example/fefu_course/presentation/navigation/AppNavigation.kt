package com.example.fefu_course.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.fefu_course.presentation.features.activity.navigation.addActivityRoot
import com.example.fefu_course.presentation.features.activity.screen.ActivityScreen
import com.example.fefu_course.presentation.features.activity.viewmodel.ActivityViewModel
import com.example.fefu_course.presentation.features.signin.navigation.addSignin
import com.example.fefu_course.presentation.features.signup.navigation.addSignup
import com.example.fefu_course.presentation.features.user.UserScreen
import com.example.fefu_course.presentation.features.user.navigation.addUserRoot
import com.example.fefu_course.presentation.features.welcome.navigation.addWelcome
import com.example.fefu_course.presentation.ui.widget.BottomNavigationBar

@Composable
fun AppNavigation(innerPaddingValues: PaddingValues) {
    val navController = rememberNavController()
    val isLoggedIn = false

    var showBottomBar by remember { mutableStateOf(false) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(currentRoute) {
        showBottomBar = when (currentRoute) {
            MainScreen.ActivityScreen.route, MainScreen.UserScreen.route -> true
            else -> false
        }
    }
    val activityViewModel = hiltViewModel<ActivityViewModel>()

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(navController, currentRoute = currentRoute ?: "")
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = if (!isLoggedIn) Root.Auth.route else BottomNavigationRoot.Activity.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            addAuthRoot(navController)
            addActivityRoot(navController, activityViewModel)
            addUserRoot(navController)
        }
    }
}

fun NavGraphBuilder.addAuthRoot(navController: NavController) {
    navigation(
        route = Root.Auth.route,
        startDestination = AuthScreen.Welcome.route
    ) {
        addWelcome(navController)
        addSignup(navController)
        addSignin(navController)
    }
}
