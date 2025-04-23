package com.example.fefu_course.presentation.features.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.fefu_course.presentation.features.user.navigation.addProfileScreen
import com.example.fefu_course.presentation.navigation.UserScreen

@Composable
fun UserScreen() {
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
}
