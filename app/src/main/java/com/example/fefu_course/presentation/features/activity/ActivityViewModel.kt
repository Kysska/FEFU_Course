package com.example.fefu_course.presentation.features.activity

import androidx.lifecycle.ViewModel
import com.example.fefu_course.domain.entity.Activity
import com.example.fefu_course.domain.entity.Comment
import com.example.fefu_course.domain.entity.DistanceUnit
import com.example.fefu_course.presentation.vo.mapper.ActivityViewMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ActivityViewModel : ViewModel() {
    private val _myState = MutableStateFlow(ActivityState())
    val myState: StateFlow<ActivityState> = _myState.asStateFlow()

    private val _userState = MutableStateFlow(ActivityState())
    val userState: StateFlow<ActivityState> = _userState.asStateFlow()

    private val myActivities = listOf<Activity>(
        Activity(
            id = 1,
            title = "Серфинг \uD83C\uDFC4",
            distance = 14.32,
            distanceUnit = DistanceUnit.KILOMETERS,
            createdAt = Date(System.currentTimeMillis() - 14 * 60 * 60 * 1000),
            accountName = "@myAccount",
            startTime = SimpleDateFormat("HH:mm", Locale.getDefault()).parse("12:49")!!,
            endTime = SimpleDateFormat("HH:mm", Locale.getDefault()).parse("17:31")!!,
            comments = listOf(
                Comment(id = 1, content = "Отличная работа!"),
            )
        ),
        Activity(
            id = 2,
            title = "Велосипед  \uD83D\uDEB2",
            distance = 1000.0,
            distanceUnit = DistanceUnit.METERS,
            createdAt = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse("29.05.2022")!!,
            accountName = "@myAccount",
            startTime = SimpleDateFormat("HH:mm", Locale.getDefault()).parse("14:49")!!,
            endTime = SimpleDateFormat("HH:mm", Locale.getDefault()).parse("16:31")!!,
            comments = listOf(
                Comment(id = 2, content = "Какой маршрут выбрали?")
            )
        ),
    )

    private val userActivities = listOf<Activity>(
        Activity(
            id = 1,
            title = "Серфинг \uD83C\uDFC4",
            distance = 14.32,
            distanceUnit = DistanceUnit.KILOMETERS,
            createdAt = Date(System.currentTimeMillis() - 14 * 60 * 60 * 1000),
            accountName = "@myAccount",
            startTime = SimpleDateFormat("HH:mm", Locale.getDefault()).parse("12:49")!!,
            endTime = SimpleDateFormat("HH:mm", Locale.getDefault()).parse("17:31")!!,
            comments = listOf(
                Comment(id = 1, content = "Отличная работа!"),
            )
        ),
        Activity(
            id = 2,
            title = "Велосипед  \uD83D\uDEB2",
            distance = 1000.0,
            distanceUnit = DistanceUnit.METERS,
            createdAt = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse("29.05.2022")!!,
            accountName = "@myAccount",
            startTime = SimpleDateFormat("HH:mm", Locale.getDefault()).parse("14:49")!!,
            endTime = SimpleDateFormat("HH:mm", Locale.getDefault()).parse("16:31")!!,
            comments = listOf(
                Comment(id = 2, content = "Какой маршрут выбрали?")
            )
        ),
        Activity(
            id = 3,
            title = "Качели",
            distance = 228.0,
            distanceUnit = DistanceUnit.METERS,
            createdAt = Date(System.currentTimeMillis() - 14 * 60 * 60 * 1000),
            accountName = "@kring",
            startTime = SimpleDateFormat("HH:mm", Locale.getDefault()).parse("17:49")!!,
            endTime = SimpleDateFormat("HH:mm", Locale.getDefault()).parse("19:31")!!,
            comments = listOf()
        )
    )

    init {
        loadMyActivities()
        loadUserActivities()
    }

    private fun loadMyActivities() {
        _myState.update {
            it.copy(
                activities = myActivities.map { activityList -> ActivityViewMapper.map(activityList) }
            )
        }
    }

    private fun loadUserActivities() {
        _userState.update {
            it.copy(
                activities = userActivities.map { activityList -> ActivityViewMapper.map(activityList) }
            )
        }
    }
}