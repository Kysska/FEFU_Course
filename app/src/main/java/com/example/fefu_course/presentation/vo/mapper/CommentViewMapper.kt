package com.example.fefu_course.presentation.vo.mapper

import com.example.fefu_course.domain.entity.Comment
import com.example.fefu_course.presentation.vo.CommentView

object CommentViewMapper : ViewMapper<Comment, CommentView> {
    override fun map(from: Comment): CommentView {
        return CommentView(
            id = from.id,
            content = from.content
        )
    }
}