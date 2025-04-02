package com.example.fefu_course.presentation.features.activity

import androidx.compose.runtime.Composable
import com.example.fefu_course.presentation.ui.widget.EmptyActivities
import com.example.fefu_course.presentation.ui.widget.LazyColumnActivity

@Composable
fun MyActivity(myActivityState: ActivityState) {
    if (myActivityState.activities.isEmpty()) {
        EmptyActivities()
    } else {
        LazyColumnActivity(activities = myActivityState.activities, userName = false)
    }
}
