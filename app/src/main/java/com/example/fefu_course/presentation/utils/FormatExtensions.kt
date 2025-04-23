package com.example.fefu_course.presentation.utils

import com.example.fefu_course.presentation.vo.ActivityView

fun List<ActivityView>.groupActivitiesByDate(): List<Pair<String, List<ActivityView>>> {
    return this
        .groupBy { activity ->
            activity.createdAt
        }
        .toList()
}
