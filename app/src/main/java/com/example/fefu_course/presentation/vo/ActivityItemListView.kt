package com.example.fefu_course.presentation.vo

data class ActivityItemListView(
    val id: Int,
    val title: String,
    val distance: String,
    val duration: String,
    val createdAt: String,
    val createdDate: String,
    val accountName: String,
    val startTime: String,
    val endTime: String,
    val comments: List<CommentView>
)
