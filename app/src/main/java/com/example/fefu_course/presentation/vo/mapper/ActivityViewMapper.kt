package com.example.fefu_course.presentation.vo.mapper

import com.example.fefu_course.domain.entity.Activity
import com.example.fefu_course.domain.entity.DistanceUnit
import com.example.fefu_course.presentation.vo.ActivityItemListView

object ActivityViewMapper : ViewMapper<Activity, ActivityItemListView> {
    override fun map(from: Activity): ActivityItemListView {
        return ActivityItemListView(
            id = from.id,
            title = from.title,
            icon = from.icon,
            distance = formatDistance(from.distance, from.distanceUnit),
            duration = formatDuration(from.duration),
            createdAt = from.createdAt,
            accountName = from.accountName,
            startTime = from.startTime,
            endTime = from.endTime,
            comments = CommentViewMapper.map(from.comments)
        )
    }

    private fun formatDistance(distance: Double, unit: DistanceUnit): String {
        return when (unit) {
            DistanceUnit.METERS -> "${"%.0f".format(distance)} m"
            DistanceUnit.KILOMETERS -> "${"%.2f".format(distance)} km"
        }
    }

    private fun formatDuration(durationMs: Long): String {
        val hours = durationMs / 1000 / 3600
        val minutes = (durationMs / 1000 % 3600) / 60
        return "${hours}h ${minutes}m"
    }
}