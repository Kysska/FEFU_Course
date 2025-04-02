package com.example.fefu_course.presentation.features.activity

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.fefu_course.R
import com.example.fefu_course.presentation.ui.theme.Typography
import com.example.fefu_course.presentation.ui.widget.LazyColumnActivity

@Composable
fun UserActivity(userActivityState: ActivityState) {
    if (userActivityState.activities.isEmpty()) {
        Text(text = stringResource(R.string.empty_my_activities), style = Typography.displayLarge, textAlign = TextAlign.Center)
        Text(text = stringResource(R.string.empty_my_activities_desc), style = Typography.bodySmall, textAlign = TextAlign.Center)
    } else {
        LazyColumnActivity(activities = userActivityState.activities, userName = true)
    }
}
