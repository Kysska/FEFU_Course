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

val Activity.duration: String
    get() = formatDuration(startTime, endTime)

val LocalDateTime.createdDate: String
    get() = formatDate(this)

val Activity.formattedCreatedAt: String
    get() = formatCreatedAt(createdAt)

val Activity.formattedTime: String
    get() = formatTime(startTime)

fun formatDistance(distance: Double, unit: DistanceUnit): String {
    return when (unit) {
        DistanceUnit.METERS -> "${"%.0f".format(distance)} м"
        DistanceUnit.KILOMETERS -> "${"%.2f".format(distance)} км"
    }
}

fun formatDuration(startTime: LocalDateTime, endTime: LocalDateTime): String {
    val duration = Duration.between(startTime, endTime)
    val hours = duration.toHours()
    val minutes = duration.toMinutes() % 60

    return "$hours ${pluralize(hours.toInt(), "час", "часа", "часов")} $minutes ${pluralize(minutes.toInt(), "минута", "минуты", "минут")}"
}

fun formatCreatedAt(createdAt: LocalDateTime): String {
    val now = LocalDateTime.now()
    val diffInMillis = ChronoUnit.MILLIS.between(createdAt, now)
    val diffInHours = diffInMillis / (60 * 60 * 1000)

    return if (diffInHours < 24) {
        when {
            diffInHours == 0L -> "Только что"
            else -> "$diffInHours ${pluralize(diffInHours.toInt(), "час", "часа", "часов")} назад"
        }
    } else {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        createdAt.format(formatter)
    }
}

fun formatDate(createdAt: LocalDateTime): String {
    val now = LocalDateTime.now()
    val diffInDays = ChronoUnit.DAYS.between(createdAt, now)
    val diffInWeeks = diffInDays / 7
    val diffInMonths = ChronoUnit.MONTHS.between(createdAt, now)
    val diffInYears = ChronoUnit.YEARS.between(createdAt, now)

    return when {
        createdAt.year == now.year && createdAt.dayOfYear == now.dayOfYear -> "Сегодня"

        createdAt.year == now.year && createdAt.dayOfYear == now.dayOfYear - 1 -> "Вчера"

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

fun formatTime(time: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return time.format(formatter)
}

fun pluralize(count: Int, one: String, few: String, many: String): String {
    return when (count % 100) {
        11, 12, 13, 14 -> many
        else -> when (count % 10) {
            1 -> one
            2, 3, 4 -> few
            else -> many
        }
    }
}
