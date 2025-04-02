package com.example.fefu_course.domain.entity

import java.util.Date

data class Activity(
    val id: Int = 0,
    val title: String = "",
    val distance: Double = 0.0,
    val distanceUnit: DistanceUnit = DistanceUnit.METERS,
    val createdAt: Date = Date(),
    val accountName: String = "",
    val startTime: Date = Date(),
    val endTime: Date = Date(),
    val comments: List<Comment> = emptyList()
)

enum class DistanceUnit {
    METERS,
    KILOMETERS
}
