package com.example.fefu_course.domain.entity

import java.time.LocalDateTime


data class Activity(
    val id: Int = 0,
    val title: String = "",
    val distance: Double = 0.0,
    val distanceUnit: DistanceUnit = DistanceUnit.METERS,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val accountName: String? = null,
    val startTime: LocalDateTime = LocalDateTime.now(),
    val endTime: LocalDateTime = LocalDateTime.now(),
    val comments: List<Comment> = emptyList()
)

enum class DistanceUnit {
    METERS,
    KILOMETERS
}