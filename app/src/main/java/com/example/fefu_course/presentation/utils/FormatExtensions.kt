package com.example.fefu_course.presentation.utils

import com.example.fefu_course.domain.entity.Activity
import java.time.LocalDateTime

fun List<Activity>.groupActivitiesByDate(): List<Pair<String, List<Activity>>> {
    return this
        .groupBy { activity ->
            activity.formattedCreatedDate
        }
        .toList()
}
