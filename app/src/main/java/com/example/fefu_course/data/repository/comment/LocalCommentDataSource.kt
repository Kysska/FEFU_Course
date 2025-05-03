package com.example.fefu_course.data.repository.comment

import com.example.fefu_course.domain.entity.Comment

interface LocalCommentDataSource {
    suspend fun insertComment(comment: Comment, activityId: Int)
    suspend fun deleteComment(comment: Comment, activityId: Int)
}
