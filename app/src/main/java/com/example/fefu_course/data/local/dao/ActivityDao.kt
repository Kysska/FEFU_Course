package com.example.fefu_course.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.fefu_course.data.local.dto.ActivityCommentCrossRef
import com.example.fefu_course.data.local.dto.ActivityDB
import com.example.fefu_course.data.local.dto.ActivityWithCommentDb
import com.example.fefu_course.data.local.dto.CommentDB
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivity(activity: ActivityDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: CommentDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivityCommentCrossRef(crossRef: ActivityCommentCrossRef)

    @Delete
    suspend fun deleteActivity(activity: ActivityDB)

    @Delete
    suspend fun deleteComment(comment: CommentDB)

    @Delete
    suspend fun deleteActivityCommentCrossRef(crossRef: ActivityCommentCrossRef)

    @Transaction
    @Query("SELECT * FROM activity WHERE my_activity = :myActivity ORDER BY created_at")
    fun getAllMyActivity(myActivity: Boolean): Flow<List<ActivityWithCommentDb>>

    @Transaction
    @Query("SELECT * FROM activity  WHERE my_activity = :myActivity ORDER BY created_at")
    fun getAllUserActivity(myActivity: Boolean): Flow<List<ActivityWithCommentDb>>

    @Transaction
    @Query("SELECT * FROM activity WHERE activity_id = :id")
    fun getActivity(id: Int): Flow<ActivityWithCommentDb>
}
