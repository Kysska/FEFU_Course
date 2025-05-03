package com.example.fefu_course.presentation.utils

import com.example.fefu_course.domain.entity.Activity
import com.example.fefu_course.domain.entity.DistanceUnit
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

val Activity.formattedDistance: String
    get() = formatDistance(distance, distanceUnit)

val Activity.formattedDuration: String
    get() = formatDuration(startTime, endTime)

val Activity.formattedCreatedDate: String
    get() = formatDate(createdAt)

val Activity.formattedCreatedAt: String
    get() = formatCreatedAt(createdAt)

val Activity.formattedStartTime: String
    get() = formatTime(startTime)

val Activity.formattedEndTime: String
    get() = formatTime(endTime)

private fun formatDistance(distance: Double, unit: DistanceUnit): String {
    return when (unit) {
        DistanceUnit.METERS -> "${"%.0f".format(distance)} м"
        DistanceUnit.KILOMETERS -> "${"%.2f".format(distance)} км"
    }
}

private fun formatDuration(startTime: LocalDateTime, endTime: LocalDateTime): String {
    val duration = Duration.between(startTime, endTime)
    val hours = duration.toHours()
    val minutes = duration.toMinutes() % 60

    return "$hours ${pluralize(hours.toInt(), "час", "часа", "часов")} $minutes ${pluralize(minutes.toInt(), "минута", "минуты", "минут")}"
}

private fun formatCreatedAt(createdAt: LocalDateTime): String {
    val now = LocalDateTime.now()
    val diffInHours = ChronoUnit.HOURS.between(createdAt, now)

    return when {
        diffInHours < 1 -> "Только что"
        diffInHours < 24 -> "$diffInHours ${pluralize(diffInHours.toInt(), "час", "часа", "часов")} назад"
        else -> createdAt.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    }
}


private fun formatDate(createdAt: LocalDateTime): String {
    val now = LocalDateTime.now()
    val diffInDays = ChronoUnit.DAYS.between(createdAt, now)
    val diffInWeeks = diffInDays / 7
    val diffInMonths = ChronoUnit.MONTHS.between(createdAt, now)
    val diffInYears = ChronoUnit.YEARS.between(createdAt, now)

    return when {
        createdAt.year == now.year && createdAt.dayOfYear == now.dayOfYear -> "Сегодня"

        createdAt.year == now.year && createdAt.dayOfYear == now.minusDays(1).dayOfYear -> "Вчера"

        diffInDays in 2..7 -> "$diffInDays ${pluralize(diffInDays.toInt(), "день", "дня", "дней")} назад"

        diffInWeeks in 1..4 -> "$diffInWeeks ${pluralize(diffInWeeks.toInt(), "неделю", "недели", "недель")} назад"

        diffInMonths in 1..11 -> "$diffInMonths ${pluralize(diffInMonths.toInt(), "месяц", "месяца", "месяцев")} назад"

        diffInYears >= 1 -> {
            val formatter = DateTimeFormatter.ofPattern("LLLL yyyy")
            val dateStr = createdAt.format(formatter)
            "${dateStr.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }} года"
        }

        else -> "Недавно"
    }
}

private fun formatTime(time: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return time.format(formatter)
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
