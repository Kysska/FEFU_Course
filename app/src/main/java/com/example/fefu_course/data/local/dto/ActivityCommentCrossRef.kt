package com.example.fefu_course.data.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = DatabaseConstName.ActivityCommentCrossRef.TABLE_NAME,
    primaryKeys = [DatabaseConstName.ActivityCommentCrossRef.COLUMN_ACTIVITY_ID, DatabaseConstName.ActivityCommentCrossRef.COLUMN_COMMENT_ID]
)
data class ActivityCommentCrossRef(
    @ColumnInfo(name = DatabaseConstName.ActivityCommentCrossRef.COLUMN_ACTIVITY_ID)
    val activityId: Int,
    @ColumnInfo(name = DatabaseConstName.ActivityCommentCrossRef.COLUMN_COMMENT_ID)
    val commentId: Int
)
