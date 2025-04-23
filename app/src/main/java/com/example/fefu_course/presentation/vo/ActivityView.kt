package com.example.fefu_course.presentation.vo

import java.util.UUID

data class ActivityView(
    val id: Int = UUID.randomUUID().hashCode(),
    val title: String = "",
    val distance: String = "",
    val duration: String = "",
    val createdAt: String = "",
    val createdDate: String = "",
    val accountName: String? = null,
    val startTime: String = "",
    val endTime: String = "",
    val comments: List<CommentView> = listOf()
)
