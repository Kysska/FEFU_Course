package com.example.fefu_course.domain.entity

import java.time.LocalDateTime
import java.util.UUID

data class Activity(
    val id: Int = UUID.randomUUID().hashCode(),
    val title: String = "",
    val distance: Double = 0.0,
    val distanceUnit: DistanceUnit = DistanceUnit.METERS,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val accountName: String = "",
    val myActivities: Boolean = false,
    val startTime: LocalDateTime = LocalDateTime.now(),
    val endTime: LocalDateTime = LocalDateTime.now(),
    val comments: List<Comment> = emptyList()
)

enum class DistanceUnit {
    METERS,
    KILOMETERS
}
