package com.example.fefu_course.presentation.features.activity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fefu_course.domain.ActivityRepository
import com.example.fefu_course.domain.CommentRepository
import com.example.fefu_course.domain.entity.Activity
import com.example.fefu_course.domain.entity.Comment
import com.example.fefu_course.presentation.features.activity.state.ActivityState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val activityRepository: ActivityRepository,
    private val commentRepository: CommentRepository
) : ViewModel() {
    private val _myState = MutableStateFlow(ActivityState())
    val myState: StateFlow<ActivityState> = _myState.asStateFlow()

    private val _userState = MutableStateFlow(ActivityState())
    val userState: StateFlow<ActivityState> = _userState.asStateFlow()

    private val _activityState = MutableStateFlow(Activity())
    val activityState: StateFlow<Activity> = _activityState.asStateFlow()

    init {
        getMyActivity()
        getUserActivity()
    }

    fun getActivityById(id: Int) {
        viewModelScope.launch {
            activityRepository.getActivity(idActivity = id)
                .collect { activity ->
                    _activityState.update {
                        activity
                    }
                }
        }
    }

    private fun getMyActivity() {
        viewModelScope.launch {
            activityRepository.getActivities(myActivities = true)
                .onStart { _myState.update { it.copy(isLoading = true) } }
                .catch { error ->
                    _myState.update {
                        it.copy(
                            isLoading = false,
                            isError = error.message ?: ""
                        )
                    }
                }
                .collect { activities ->
                    _myState.update {
                        it.copy(
                            isLoading = false,
                            isError = "",
                            activities = activities
                        )
                    }
                }
        }
    }

    private fun getUserActivity() {
        viewModelScope.launch {
            activityRepository.getActivities(myActivities = false)
                .onStart { _userState.update { it.copy(isLoading = true) } }
                .catch { error ->
                    _userState.update {
                        it.copy(
                            isLoading = false,
                            isError = error.message ?: ""
                        )
                    }
                }
                .collect { activities ->
                    _userState.update {
                        it.copy(
                            isLoading = false,
                            isError = "",
                            activities = activities
                        )
                    }
                }
        }
    }

    fun addComment(comment: Comment, activityId: Int) {
        viewModelScope.launch {
            try {
                commentRepository.addComment(comment, activityId)
                _activityState.update { currentState ->
                    val updatedComments = currentState.comments.toMutableList().apply {
                        add(comment)
                    }
                    currentState.copy(comments = updatedComments)
                }
            } catch (e: Exception) {
                _activityState.update { currentState ->
                    currentState.copy(comments = currentState.comments)
                }
            }
        }
    }
}
