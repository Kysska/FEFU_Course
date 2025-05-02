package com.example.fefu_course.data.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = DatabaseConstName.Activity.TABLE_NAME)
data class ActivityDB(
    @PrimaryKey
    @ColumnInfo(name = DatabaseConstName.Activity.COLUMN_ID)
    val id: Int,
    @ColumnInfo(name = DatabaseConstName.Activity.COLUMN_TITLE)
    val title: String,
    @ColumnInfo(name = DatabaseConstName.Activity.COLUMN_DISTANCE)
    val distance: Double,
    @ColumnInfo(name = DatabaseConstName.Activity.COLUMN_DISTANCE_UNIT)
    val distanceUnit: String,
    @ColumnInfo(name = DatabaseConstName.Activity.COLUMN_CREATED_AT)
    val createdAt: LocalDateTime,
    @ColumnInfo(name = DatabaseConstName.Activity.COLUMN_ACCOUNT_NAME)
    val accountName: String,
    @ColumnInfo(name = DatabaseConstName.Activity.COLUMN_MY_ACTIVITY)
    val myActivities: Boolean,
    @ColumnInfo(name = DatabaseConstName.Activity.COLUMN_START_TIME)
    val startTime: LocalDateTime,
    @ColumnInfo(name = DatabaseConstName.Activity.COLUMN_END_TIME)
    val endTime: LocalDateTime
)