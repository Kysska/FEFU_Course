package com.example.fefu_course.presentation.vo

import java.util.UUID

data class CommentView(
    val id: Int = UUID.randomUUID().hashCode(),
    val content: String = ""
)
