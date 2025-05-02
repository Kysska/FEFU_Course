package com.example.fefu_course.data.repository.activity

import com.example.fefu_course.domain.ActivityRepository
import com.example.fefu_course.domain.entity.Activity
import kotlinx.coroutines.flow.Flow

class ActivityRepositoryImpl(
    private val localActivityDataSource: LocalActivityDataSource
) : ActivityRepository {

    override suspend fun saveActivity(activity: Activity) {
        return localActivityDataSource.insertActivity(activity)
    }

    override suspend fun deleteActivity(activity: Activity) {
        return localActivityDataSource.deleteActivity(activity)
    }

    override fun getActivity(idActivity: Int): Flow<Activity> {
        return localActivityDataSource.getActivity(idActivity)
    }

    override fun getActivities(myActivities: Boolean): Flow<List<Activity>> {
        return localActivityDataSource.getActivities(myActivities)
    }
}
