package com.example.fefu_course.presentation.vo

data class ActivityItemListView(
    val id: Int = 0,
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
