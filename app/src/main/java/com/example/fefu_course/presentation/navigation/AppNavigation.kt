package com.example.fefu_course.presentation.navigation

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fefu_course.presentation.features.activity.screen.ActivityScreen
import com.example.fefu_course.presentation.features.activity.screen.DetailActivityScreen
import com.example.fefu_course.presentation.features.activity.screen.MyActivity
import com.example.fefu_course.presentation.features.activity.screen.UserActivity
import com.example.fefu_course.presentation.features.activity.viewmodel.ActivityViewModel
import com.example.fefu_course.presentation.features.signin.SignInScreen
import com.example.fefu_course.presentation.features.signin.SignInViewModel
import com.example.fefu_course.presentation.features.signup.SignUpScreen
import com.example.fefu_course.presentation.features.signup.SignUpViewModel
import com.example.fefu_course.presentation.features.user.ProfileScreen
import com.example.fefu_course.presentation.features.user.UserScreen
import com.example.fefu_course.presentation.features.welcome.WelcomeScreen
import com.example.fefu_course.presentation.ui.widget.BottomNavigationBar

@Composable
fun AppNavigation(innerPaddingValues: PaddingValues, activity: ComponentActivity) {
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
            addAuthRoot(navController, activity)
            addActivityRoot(navController, activity)
            addUserRoot(navController)
        }
    }
}

private fun NavGraphBuilder.addAuthRoot(navController: NavController, activity: ComponentActivity) {
    navigation(
        route = Root.Auth.route,
        startDestination = AuthScreen.Welcome.route
    ) {
        addWelcome(navController)
        addSignup(navController, activity)
        addSignin(navController, activity)
    }
}

fun NavGraphBuilder.addActivityRoot(navController: NavController, activity: ComponentActivity) {
    navigation(
        route = BottomNavigationRoot.Activity.route,
        startDestination = MainScreen.ActivityScreen.route
    ) {
        val viewModel: ActivityViewModel =
            ViewModelProvider(activity).get(ActivityViewModel::class.java)
        addActivityScreen(navController, viewModel)
        addActivityDetailScreen(navController, viewModel)
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

private fun NavGraphBuilder.addSignup(navController: NavController, activity: ComponentActivity) {
    composable(AuthScreen.SignUp.route) {
        val viewModel: SignUpViewModel =
            ViewModelProvider(activity).get(SignUpViewModel::class.java)
        val state by viewModel.state.collectAsState()
        SignUpScreen(signUpState = state,
            navController = navController,
            onLoginChanged = { viewModel.onLoginChanged(it) },
            onPasswordChanged = { viewModel.onPasswordChanged(it) },
            onNameChanged = { viewModel.onNameChanged(it) },
            onRetryPasswordChanged = { viewModel.onPasswordRetryChanged(it) },
            onGenderChanged = { viewModel.onGenderChanged(it) },
            onSignUp = { viewModel.signUp(state) }
        )
    }
}

private fun NavGraphBuilder.addSignin(navController: NavController, activity: ComponentActivity) {
    composable(AuthScreen.SignIn.route) {
        val viewModel: SignInViewModel =
            ViewModelProvider(activity).get(SignInViewModel::class.java)
        val state by viewModel.state.collectAsState()

        SignInScreen(state = state,
            navController = navController,
            onLoginChanged = { viewModel.onLoginChanged(it) },
            onPasswordChanged = { viewModel.onPasswordChanged(it) },
            onSignIn = { viewModel.signIn(state) })
    }
}

fun NavGraphBuilder.addUserScreen(navController: NavController) {
    composable(MainScreen.UserScreen.route) {
        UserScreen(navController)
    }
}

private fun NavGraphBuilder.addActivityScreen(
    navController: NavController,
    viewModel: ActivityViewModel
) {
    composable(MainScreen.ActivityScreen.route) {
        ActivityScreen(navController, viewModel)
    }
}

fun NavGraphBuilder.addMyActivityScreen(
    navController: NavController,
    viewModel: ActivityViewModel
) {
    composable(ActivityScreen.MyActivity.route) {
        val state by viewModel.myState.collectAsState()
        MyActivity(navController, state)
    }
}

fun NavGraphBuilder.addUserActivityScreen(
    navController: NavController,
    viewModel: ActivityViewModel
) {
    composable(ActivityScreen.UserActivity.route) {
        val state by viewModel.userState.collectAsState()
        UserActivity(navController, state)
    }
}

fun NavGraphBuilder.addProfileScreen() {
    composable(UserScreen.ProfileScreen.route) {
        ProfileScreen()
    }
}

fun NavGraphBuilder.addActivityDetailScreen(navController: NavController, viewModel: ActivityViewModel) {
    composable(
        route = MainScreen.ActivityDetail.route,
        arguments = listOf(navArgument("activityId") { type = NavType.IntType })
    ) { entry ->
        val activityId = entry.arguments?.getInt("activityId") ?: return@composable
        DetailActivityScreen(navController, activityId, viewModel)
    }
}
