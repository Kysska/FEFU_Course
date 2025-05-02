package com.example.fefu_course.domain.entity

import java.util.UUID

data class Comment(
    val id: Int = UUID.randomUUID().hashCode(),
    val content: String = ""
)
