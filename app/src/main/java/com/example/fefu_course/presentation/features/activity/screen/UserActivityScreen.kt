package com.example.fefu_course.presentation.features.activity.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.fefu_course.R
import com.example.fefu_course.presentation.features.activity.state.ActivityState
import com.example.fefu_course.presentation.ui.theme.Typography
import com.example.fefu_course.presentation.ui.widget.LazyColumnActivity

@Composable
fun UserActivity(navController: NavController, userActivityState: ActivityState) {
    if (userActivityState.activities.isEmpty()) {
        Text(text = stringResource(R.string.my_activities_empty), style = Typography.displayLarge, textAlign = TextAlign.Center)
        Text(text = stringResource(R.string.my_activities_desc_empty), style = Typography.bodySmall, textAlign = TextAlign.Center)
    } else {
        LazyColumnActivity(navController, activities = userActivityState.activities)
    }
}
