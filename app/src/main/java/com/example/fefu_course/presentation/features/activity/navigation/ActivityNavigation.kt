package com.example.fefu_course.presentation.features.activity.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.fefu_course.presentation.features.activity.screen.ActivityScreen
import com.example.fefu_course.presentation.features.activity.screen.DetailActivityScreen
import com.example.fefu_course.presentation.features.activity.screen.MyActivity
import com.example.fefu_course.presentation.features.activity.screen.UserActivity
import com.example.fefu_course.presentation.features.activity.state.ActivityState
import com.example.fefu_course.presentation.features.activity.viewmodel.ActivityViewModel
import com.example.fefu_course.presentation.navigation.ActivityTab
import com.example.fefu_course.presentation.navigation.BottomNavigationRoot
import com.example.fefu_course.presentation.navigation.MainScreen

fun NavGraphBuilder.addActivityRoot(
    navController: NavController,
    activityViewModel: ActivityViewModel
) {
    navigation(
        route = BottomNavigationRoot.Activity.route,
        startDestination = MainScreen.ActivityScreen.route
    ) {
        addActivityScreen(navController, activityViewModel)
        addActivityDetailScreen(navController, activityViewModel)
    }
}

fun NavGraphBuilder.addActivityScreen(
    navController: NavController,
    viewModel: ActivityViewModel
) {
    composable(MainScreen.ActivityScreen.route) {
        val myState by viewModel.myState.collectAsState()
        val userState by viewModel.userState.collectAsState()
        ActivityScreen(myState, userState) { id: Int ->
            navController.navigate(MainScreen.ActivityDetail.createRoute(id))
        }
    }
}

fun NavGraphBuilder.addMyActivityScreen(
    myState: ActivityState,
    onNavigateToActivityDetail: (id: Int) -> Unit
) {
    composable(ActivityTab.MyActivity.route) {
        MyActivity(myState) { id ->
            onNavigateToActivityDetail(id)
        }
    }
}

fun NavGraphBuilder.addUserActivityScreen(
    userState: ActivityState,
    onNavigateToActivityDetail: (id: Int) -> Unit
) {
    composable(ActivityTab.UserActivity.route) {
        UserActivity(userState, onNavigateToActivityDetail)
    }
}

fun NavGraphBuilder.addActivityDetailScreen(
    navController: NavController,
    viewModel: ActivityViewModel
) {
    composable(
        route = MainScreen.ActivityDetail.route,
        arguments = listOf(navArgument(MainScreen.ACTIVITY_DETAIL_ID) { type = NavType.IntType })
    ) { entry ->
        val activityId = entry.arguments?.getInt(MainScreen.ACTIVITY_DETAIL_ID) ?: return@composable

        LaunchedEffect(activityId) {
            viewModel.getActivityById(activityId)
        }
        val activityState by viewModel.activityState.collectAsState()

        DetailActivityScreen(
            activityState = activityState,
            onAddComment = { comment ->
                viewModel.addComment(comment, activityId)
            },
            onNavigateBack = { navController.navigateUp() }
        )
    }
}
