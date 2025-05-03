package com.example.fefu_course.data.local.dto.mapper

import com.example.fefu_course.data.local.dto.CommentDB
import com.example.fefu_course.domain.entity.Comment

object CommentMapper : DatabaseMapper<Comment, CommentDB> {
    override fun map(from: Comment): CommentDB {
        return CommentDB(
            id = from.id,
            content = from.content
        )
    }

    override fun reverseMap(to: CommentDB): Comment {
        return Comment(
            id = to.id,
            content = to.content
        )
    }
}
