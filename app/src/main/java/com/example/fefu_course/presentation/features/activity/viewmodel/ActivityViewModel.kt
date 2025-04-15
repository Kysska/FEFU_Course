package com.example.fefu_course.presentation.features.activity.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fefu_course.presentation.features.activity.state.ActivityState
import com.example.fefu_course.presentation.vo.ActivityView
import com.example.fefu_course.presentation.vo.CommentView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor() : ViewModel() {
    private val _myState = MutableStateFlow(ActivityState())
    val myState: StateFlow<ActivityState> = _myState.asStateFlow()

    private val _userState = MutableStateFlow(ActivityState())
    val userState: StateFlow<ActivityState> = _userState.asStateFlow()

    private val _activityState = MutableStateFlow(ActivityView())
    val activityState: StateFlow<ActivityView> = _activityState.asStateFlow()

    private val myActivities = listOf<ActivityView>(
        ActivityView(
            id = 1,
            title = "Серфинг \uD83C\uDFC4",
            distance = "14.32 км",
            createdDate = "14 часов назад",
            createdAt = "Вчера",
            accountName = null,
            duration = "2 часа 46 минут",
            startTime = "12:36",
            endTime = "17:31",
            comments = listOf(
                CommentView(id = 1, content = "Отличная работа!")
            )
        ),
        ActivityView(
            id = 2,
            title = "Велосипед  \uD83D\uDEB2",
            distance = "1 000 м",
            createdDate = "29.05.2022",
            createdAt = "Май 2022 года",
            duration = "60 минут",
            accountName = null,
            startTime = "14:39",
            endTime = "16:31",
            comments = listOf(
                CommentView(id = 2, content = "Какой маршрут выбрали?")
            )
        )
    )

    private val userActivities = listOf<ActivityView>(
        ActivityView(
            id = 3,
            title = "Серфинг",
            distance = "14.32 км",
            createdDate = "14 часов назад",
            createdAt = "Вчера",
            accountName = "@skfjeiijf",
            duration = "2 часа 46 минут",
            startTime = "14:36",
            endTime = "17:31",
            comments = listOf(
                CommentView(id = 3, content = "Отличная работа!")
            )
        ),
        ActivityView(
            id = 4,
            title = "Качели",
            distance = "228 м",
            createdDate = "14 часов назад",
            createdAt = "Вчера",
            accountName = "@548dg",
            duration = "14 часов 48 минут",
            startTime = "12:36",
            endTime = "03:12",
            comments = listOf(
                CommentView(id = 4, content = "Отличная работа!")
            )
        ),
        ActivityView(
            id = 5,
            title = "Езда на кадилак",
            distance = "10 км",
            createdDate = "14 часов назад",
            createdAt = "Вчера",
            accountName = "@ioeio67",
            duration = "1 час 10 минут",
            startTime = "12:36",
            endTime = "13:46",
            comments = listOf(
                CommentView(id = 5, content = "Отличная работа!")
            )
        ),
    )

    init {
        loadMyActivities()
        loadUserActivities()
    }

    private fun loadMyActivities() {
        _myState.update {
            it.copy(
                activities = myActivities
            )
        }
    }

    private fun loadUserActivities() {
        _userState.update {
            it.copy(
                activities = userActivities
            )
        }
    }

    fun getActivityById(id: Int) {
        val activity =
            userActivities.find { it.id == id } ?: myActivities.find { it.id == id } ?: ActivityView()
        _activityState.update {
            activity
        }
    }

    fun addComment(comment: CommentView) {
        _activityState.update { currentState ->
            val updatedComments = currentState.comments.toMutableList().apply {
                add(comment)
            }
            currentState.copy(comments = updatedComments)
        }
    }
}