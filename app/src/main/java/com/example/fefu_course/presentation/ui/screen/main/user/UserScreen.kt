package com.example.fefu_course.presentation.ui.screen.main.user

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.fefu_course.presentation.navigation.UserScreen
import com.example.fefu_course.presentation.navigation.addProfileScreen

@Composable
fun UserScreen(navController: NavController) {
    val userController = rememberNavController()

    Column {
        NavHost(
            navController = userController,
            startDestination = UserScreen.ProfileScreen.route,
            modifier = Modifier.fillMaxSize()
        ) {
            addProfileScreen()
        }
    }

    BackHandler {
        navController.popBackStack()
    }
}
