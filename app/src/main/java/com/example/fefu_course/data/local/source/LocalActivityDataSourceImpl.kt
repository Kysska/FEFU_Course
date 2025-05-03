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
        val commentDBs = activity.comments.map { databaseCommentMapper.map(it) }
        val crossRefs = activity.comments.map { comment ->
            ActivityCommentCrossRef(activity.id, comment.id)
        }

        dao.insertFullActivity(activityDB.activity, commentDBs, crossRefs)
    }

    override suspend fun deleteActivity(activity: Activity) {
        val activityDB = databaseActivityMapper.map(activity)
        val commentDBs = activity.comments.map { databaseCommentMapper.map(it) }
        val crossRefs = activity.comments.map { comment ->
            ActivityCommentCrossRef(activity.id, comment.id)
        }

        dao.deleteFullActivity(activityDB.activity, commentDBs, crossRefs)
    }

    override fun getActivity(idActivity: Int): Flow<Activity> {
        return dao.getActivity(idActivity).map { ActivityMapper.reverseMap(it) }
    }

    override fun getActivities(myActivities: Boolean): Flow<List<Activity>> {
        return dao.getAllUserActivity(myActivities).map { ActivityMapper.reverseMap(it) }
    }
}
