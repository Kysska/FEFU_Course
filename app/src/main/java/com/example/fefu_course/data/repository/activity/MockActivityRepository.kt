package com.example.fefu_course.data.repository.activity

import com.example.fefu_course.domain.ActivityRepository
import com.example.fefu_course.domain.entity.Activity
import com.example.fefu_course.domain.entity.Comment
import com.example.fefu_course.domain.entity.DistanceUnit
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEmpty

class MockActivityRepository @Inject constructor(
    private val localActivityDataSource: LocalActivityDataSource
) : ActivityRepository {

    private val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")

    private val myMockActivities = listOf(
        Activity(
            title = "Серфинг \uD83C\uDFC4",
            distance = 14.32,
            distanceUnit = DistanceUnit.KILOMETERS,
            createdAt = LocalDateTime.now().minusHours(14).minusMinutes(15),
            startTime = LocalDateTime.now().minusHours(14).minusMinutes(30),
            endTime = LocalDateTime.now().minusHours(13).minusMinutes(20),
            myActivities = true,
            accountName = "@qwerty",
            comments = listOf(
                Comment(content = "Отличная работа!")
            )
        ),
        Activity(
            title = "Велосипед  \uD83D\uDEB2",
            distance = 1000.0,
            distanceUnit = DistanceUnit.METERS,
            createdAt = LocalDateTime.parse("23.05.2022 15:20", formatter),
            startTime = LocalDateTime.parse("23.05.2022 15:20", formatter),
            endTime = LocalDateTime.parse("23.05.2022 18:25", formatter),
            myActivities = true,
            accountName = "@qwerty",
            comments = listOf(
                Comment(content = "Какой маршрут выбрали?")
            )
        )
    )

    private val userMockActivities = listOf(
        Activity(
            title = "Серфинг",
            distance = 14.32,
            distanceUnit = DistanceUnit.KILOMETERS,
            createdAt = LocalDateTime.now().minusHours(14).minusMinutes(15),
            startTime = LocalDateTime.now().minusHours(14).minusMinutes(30),
            endTime = LocalDateTime.now().minusHours(13).minusMinutes(20),
            accountName = "@qsgfhh",
            comments = listOf(
                Comment(content = "Отличная работа!")
            )
        ),
        Activity(
            title = "Качели",
            distance = 228.0,
            distanceUnit = DistanceUnit.METERS,
            createdAt = LocalDateTime.now().minusHours(14).minusMinutes(15),
            startTime = LocalDateTime.now().minusHours(14).minusMinutes(30),
            endTime = LocalDateTime.now().minusHours(13).minusMinutes(20),
            accountName = "@qsgfhh",
            comments = listOf(
                Comment(content = "Отличная работа!")
            )
        ),
        Activity(
            title = "Езда на кадилак",
            distance = 10.0,
            distanceUnit = DistanceUnit.KILOMETERS,
            createdAt = LocalDateTime.now().minusHours(14).minusMinutes(15),
            startTime = LocalDateTime.now().minusHours(14).minusMinutes(30),
            endTime = LocalDateTime.now().minusHours(13).minusMinutes(20),
            accountName = "@ioeuw",
            comments = listOf(
                Comment(content = "Какой маршрут выбрали?")
            )
        )
    )

    override suspend fun saveActivity(activity: Activity) {
        return localActivityDataSource.insertActivity(activity)
    }

    override suspend fun deleteActivity(activity: Activity) {
        return localActivityDataSource.deleteActivity(activity)
    }

    override fun getActivity(idActivity: Int): Flow<Activity> {
        return localActivityDataSource.getActivity(idActivity)
            .onEmpty {
                val mockActivity = myMockActivities.find { it.id == idActivity }
                    ?: userMockActivities.find { it.id == idActivity }
                flowOf(mockActivity ?: Activity())
            }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getActivities(myActivities: Boolean): Flow<List<Activity>> {
        return localActivityDataSource.getActivities(myActivities)
            .flatMapConcat { cached ->
                if (cached.isEmpty()) {
                    if (myActivities) {
                        flow {
                            myMockActivities.map {
                                localActivityDataSource.insertActivity(it)
                                emit(myMockActivities)
                            }
                        }
                    } else {
                        flow {
                            userMockActivities.map {
                                localActivityDataSource.insertActivity(it)
                                emit(userMockActivities)
                            }
                        }
                    }
                } else {
                    flowOf(cached)
                }
            }
    }
}
