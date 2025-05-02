package com.example.fefu_course.data.repository.comment

import com.example.fefu_course.domain.CommentRepository
import com.example.fefu_course.domain.entity.Comment
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val localCommentDataSource: LocalCommentDataSource
) : CommentRepository {
    override suspend fun addComment(comment: Comment, activityId: Int) {
        localCommentDataSource.insertComment(comment, activityId)
    }

    override suspend fun deleteComment(comment: Comment, activityId: Int) {
        localCommentDataSource.deleteComment(comment, activityId)
    }
}
