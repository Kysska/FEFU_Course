package com.example.fefu_course.data.local.dto.mapper

import com.example.fefu_course.data.local.dto.ActivityDB
import com.example.fefu_course.data.local.dto.ActivityWithCommentDb
import com.example.fefu_course.domain.entity.Activity
import com.example.fefu_course.domain.entity.DistanceUnit

object ActivityMapper : DatabaseMapper<Activity, ActivityWithCommentDb> {

    override fun map(from: Activity): ActivityWithCommentDb {
        return ActivityWithCommentDb(
            activity = ActivityDB(
                id = from.id,
                title = from.title,
                distance = from.distance,
                distanceUnit = from.distanceUnit.name,
                createdAt = from.createdAt,
                accountName = from.accountName,
                startTime = from.startTime,
                endTime = from.endTime,
                myActivities = from.myActivities
            ),
            comment = CommentMapper.map(from.comments),
        )
    }

    override fun reverseMap(to: ActivityWithCommentDb): Activity {
        return Activity(
            id = to.activity.id,
            title = to.activity.title,
            distance = to.activity.distance,
            distanceUnit = DistanceUnit.valueOf(to.activity.distanceUnit),
            createdAt = to.activity.createdAt,
            accountName = to.activity.accountName,
            startTime = to.activity.startTime,
            endTime = to.activity.endTime,
            myActivities = to.activity.myActivities,
            comments = CommentMapper.reverseMap(to.comment),
        )
    }
}
