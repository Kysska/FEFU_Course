package com.example.fefu_course.presentation.features.activity.state

import androidx.compose.runtime.Immutable
import com.example.fefu_course.domain.entity.Activity

@Immutable
data class ActivityState(
    val activities: List<Activity> = listOf(),
    val isLoading: Boolean = false,
    val isError: String = ""
)
