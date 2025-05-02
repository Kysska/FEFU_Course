package com.example.fefu_course.data.local.dto

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ActivityWithCommentDb(
    @Embedded
    val activity: ActivityDB,
    @Relation(
        parentColumn = DatabaseConstName.ActivityCommentCrossRef.COLUMN_ACTIVITY_ID,
        entityColumn = DatabaseConstName.ActivityCommentCrossRef.COLUMN_COMMENT_ID,
        associateBy = Junction(ActivityCommentCrossRef::class)
    )
    val comment: List<CommentDB>
)
