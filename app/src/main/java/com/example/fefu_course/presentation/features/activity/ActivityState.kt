package com.example.fefu_course.presentation.features.activity

import com.example.fefu_course.presentation.vo.ActivityItemListView

data class ActivityState(
    val activities: List<ActivityItemListView> = listOf(),
    val isLoading: Boolean = false,
    val isError: String = ""
)
