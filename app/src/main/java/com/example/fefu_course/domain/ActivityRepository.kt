package com.example.fefu_course.domain

import com.example.fefu_course.domain.entity.Activity
import kotlinx.coroutines.flow.Flow

interface ActivityRepository {
    suspend fun saveActivity(activity: Activity)
    suspend fun deleteActivity(activity: Activity)
    fun getActivity(idActivity: Int): Flow<Activity>
    fun getActivities(myActivities: Boolean): Flow<List<Activity>>
}
