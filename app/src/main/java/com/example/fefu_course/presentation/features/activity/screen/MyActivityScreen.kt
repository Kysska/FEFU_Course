package com.example.fefu_course.presentation.features.activity.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.fefu_course.presentation.features.activity.state.ActivityState
import com.example.fefu_course.presentation.ui.widget.EmptyActivities
import com.example.fefu_course.presentation.ui.widget.LazyColumnActivity

@Composable
fun MyActivity(navController: NavController, myActivityState: ActivityState) {
    if (myActivityState.activities.isEmpty()) {
        EmptyActivities()
    } else {
        LazyColumnActivity(navController, activities = myActivityState.activities)
    }
}
