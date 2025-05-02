package com.example.fefu_course.data.local.dto

object DatabaseConstName {
    object Activity {
        const val TABLE_NAME = "activity"
        const val COLUMN_ID = "activity_id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DISTANCE = "distance"
        const val COLUMN_DISTANCE_UNIT = "distance_unit"
        const val COLUMN_CREATED_AT = "created_at"
        const val COLUMN_ACCOUNT_NAME = "account_name"
        const val COLUMN_MY_ACTIVITY = "my_activity"
        const val COLUMN_START_TIME = "start_time"
        const val COLUMN_END_TIME = "end_time"
    }

    object Comment {
        const val TABLE_NAME = "comment"
        const val COLUMN_ID = "comment_id"
        const val COLUMN_CONTENT = "content"
    }

    object ActivityCommentCrossRef {
        const val TABLE_NAME = "activity_comment"
        const val COLUMN_ACTIVITY_ID = "activity_id"
        const val COLUMN_COMMENT_ID = "comment_id"
    }

    object ActivityWithComment {
        const val TABLE_NAME = "activity_with_comment"
    }
}
