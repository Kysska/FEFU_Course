package com.example.fefu_course.data.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DatabaseConstName.Comment.TABLE_NAME)
data class CommentDB(
    @PrimaryKey
    @ColumnInfo(name = DatabaseConstName.Comment.COLUMN_ID)
    val id: Int,
    @ColumnInfo(name = DatabaseConstName.Comment.COLUMN_CONTENT)
    val content: String
)
