package com.example.fefu_course.presentation.features.activity.state

import com.example.fefu_course.presentation.vo.ActivityView

data class ActivityState(
    val activities: List<ActivityView> = listOf(),
    val isLoading: Boolean = false,
    val isError: String = ""
)
