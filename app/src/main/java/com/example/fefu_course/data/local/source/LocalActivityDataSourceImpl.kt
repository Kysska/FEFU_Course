package com.example.fefu_course.data.local.source

import com.example.fefu_course.data.local.dao.ActivityDao
import com.example.fefu_course.data.local.dto.ActivityCommentCrossRef
import com.example.fefu_course.data.local.dto.mapper.ActivityMapper
import com.example.fefu_course.data.local.dto.mapper.CommentMapper
import com.example.fefu_course.data.repository.activity.LocalActivityDataSource
import com.example.fefu_course.domain.entity.Activity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalActivityDataSourceImpl @Inject constructor(
    private val dao: ActivityDao,
    private val databaseActivityMapper: ActivityMapper = ActivityMapper,
    private val databaseCommentMapper: CommentMapper = CommentMapper
) : LocalActivityDataSource {
    override suspend fun insertActivity(activity: Activity) {
        val activityDB = databaseActivityMapper.map(activity)

        dao.insertActivity(activityDB.activity)

        if (activity.comments.isNotEmpty()) {
            for (comment in activity.comments) {
                dao.insertComment(databaseCommentMapper.map(comment))

                val crossRef = ActivityCommentCrossRef(
                    activityId = activity.id,
                    commentId = comment.id
                )
                dao.insertActivityCommentCrossRef(crossRef)
            }
        }
    }

    override suspend fun deleteActivity(activity: Activity) {
        val activityDB = databaseActivityMapper.map(activity)

        dao.deleteActivity(activityDB.activity)

        if (activity.comments.isNotEmpty()) {
            for (comment in activity.comments) {
                dao.deleteComment(databaseCommentMapper.map(comment))

                val crossRef = ActivityCommentCrossRef(
                    activityId = activity.id,
                    commentId = comment.id
                )
                dao.deleteActivityCommentCrossRef(crossRef)
            }
        }
    }

    override fun getActivity(idActivity: Int): Flow<Activity> {
        return dao.getActivity(idActivity).map { ActivityMapper.reverseMap(it) }
    }

    override fun getActivities(myActivities: Boolean): Flow<List<Activity>> {
        if (myActivities) {
            return dao.getAllMyActivity(myActivities).map { ActivityMapper.reverseMap(it) }
        } else {
            return dao.getAllUserActivity(myActivities).map { ActivityMapper.reverseMap(it) }
        }
    }
}
