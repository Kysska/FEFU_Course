package com.example.fefu_course.presentation.vo.mapper

import com.example.fefu_course.domain.entity.Activity
import com.example.fefu_course.domain.entity.DistanceUnit
import com.example.fefu_course.presentation.vo.ActivityItemListView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object ActivityViewMapper : ViewMapper<Activity, ActivityItemListView> {
    override fun map(from: Activity): ActivityItemListView {
        return ActivityItemListView(
            id = from.id,
            title = from.title,
            distance = formatDistance(from.distance, from.distanceUnit),
            duration = formatDuration(from.startTime, from.endTime),
            createdAt = formatCreatedAt(from.createdAt),
            createdDate = formatDate(from.createdAt),
            accountName = from.accountName,
            startTime = formatTime(from.startTime),
            endTime = formatTime(from.endTime),
            comments = CommentViewMapper.map(from.comments)
        )
    }

    private fun formatDistance(distance: Double, unit: DistanceUnit): String {
        return when (unit) {
            DistanceUnit.METERS -> "${"%.0f".format(distance)} м"
            DistanceUnit.KILOMETERS -> "${"%.2f".format(distance)} км"
        }
    }

    private fun formatDuration(startTime: Date, endTime: Date): String {
        val duration = endTime.time - startTime.time
        val hours = duration / 1000 / 3600
        val minutes = (duration / 1000 % 3600) / 60
        return "$hours ${pluralize(hours.toInt(), "час", "часа", "часов")} $minutes ${pluralize(minutes.toInt(), "минуту", "минуты", "минут")}"
    }

    private fun formatCreatedAt(createdAt: Date): String {
        val now = Date()
        val diffInMillis = now.time - createdAt.time
        val diffInHours = diffInMillis / (1000 * 60 * 60)

        return if (diffInHours < 24) {
            when {
                diffInHours == 0L -> "Только что"
                else -> "$diffInHours ${pluralize(diffInHours.toInt(), "час", "часа", "часов")} назад"
            }
        } else {
            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            dateFormat.format(createdAt)
        }
    }

    private fun formatDate(createdAt: Date): String {
        val now = Calendar.getInstance()
        val createdCal = Calendar.getInstance().apply { time = createdAt }

        val diffInMillis = now.timeInMillis - createdCal.timeInMillis
        val diffInDays = diffInMillis / (1000 * 60 * 60 * 24)
        val diffInWeeks = diffInDays / 7
        val diffInMonths = (now[Calendar.MONTH] - createdCal[Calendar.MONTH]) +
                12 * (now[Calendar.YEAR] - createdCal[Calendar.YEAR])
        val diffInYears = now[Calendar.YEAR] - createdCal[Calendar.YEAR]

        return when {
            now[Calendar.YEAR] == createdCal[Calendar.YEAR] &&
                    now[Calendar.DAY_OF_YEAR] == createdCal[Calendar.DAY_OF_YEAR] -> "Сегодня"

            now[Calendar.YEAR] == createdCal[Calendar.YEAR] &&
                    now[Calendar.DAY_OF_YEAR] - createdCal[Calendar.DAY_OF_YEAR] == 1 -> "Вчера"

            diffInDays in 2..7 -> "$diffInDays ${pluralize(diffInDays.toInt(), "день", "дня", "дней")} назад"

            diffInWeeks in 1..4 -> "$diffInWeeks ${pluralize(diffInWeeks.toInt(), "неделю", "недели", "недель")} назад"

            diffInMonths in 1..11 -> "$diffInMonths ${pluralize(diffInMonths, "месяц", "месяца", "месяцев")} назад"

            diffInYears >= 1 -> {
                val dateFormat = SimpleDateFormat("LLLL yyyy", Locale.getDefault())
                val date = dateFormat.format(createdAt)
                "${date.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }} года"
            }

            else -> "Недавно"
        }
    }

    private fun pluralize(count: Int, one: String, few: String, many: String): String {
        return when (count % 100) {
            11, 12, 13, 14 -> many
            else -> when (count % 10) {
                1 -> one
                2, 3, 4 -> few
                else -> many
            }
        }
    }

    private fun formatTime(time: Date): String {
        val hours = time.time / 1000 / 3600
        val minutes = (time.time / 1000 % 3600) / 60
        return "$hours : $minutes"
    }
}
