package com.example.fefu_course.di

import com.example.fefu_course.data.local.dao.ActivityDao
import com.example.fefu_course.data.local.source.LocalActivityDataSourceImpl
import com.example.fefu_course.data.local.source.LocalCommentDataSourceImpl
import com.example.fefu_course.data.repository.activity.LocalActivityDataSource
import com.example.fefu_course.data.repository.comment.LocalCommentDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    @Singleton
    fun provideActivityLocalDataSource(activityDao: ActivityDao): LocalActivityDataSource {
        return LocalActivityDataSourceImpl(
            activityDao
        )
    }

    @Provides
    @Singleton
    fun provideCommentLocalDataSource(activityDao: ActivityDao): LocalCommentDataSource {
        return LocalCommentDataSourceImpl(
            activityDao
        )
    }
}
