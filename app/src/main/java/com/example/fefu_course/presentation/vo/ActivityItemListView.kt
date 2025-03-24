package com.example.fefu_course.presentation.vo

import java.util.Date

data class ActivityItemListView(
    val id: Int,
    val title: String,
    val icon: Int,
    val distance: String,
    val duration: String,
    val createdAt: Date,
    val accountName: String,
    val startTime: Date,
    val endTime: Date,
    val comments: List<CommentView>
)
