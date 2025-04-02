package com.example.fefu_course.presentation.utils

import com.example.fefu_course.presentation.vo.ActivityItemListView

fun List<ActivityItemListView>.groupActivitiesByDate(): List<Pair<String, List<ActivityItemListView>>> {
    return this
        .groupBy { activity ->
            activity.createdDate
        }
        .toList()
}
