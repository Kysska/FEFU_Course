package com.example.fefu_course.domain

import com.example.fefu_course.domain.entity.Comment

interface CommentRepository {
    suspend fun addComment(comment: Comment, activityId: Int)
    suspend fun deleteComment(comment: Comment, activityId: Int)
}
