package com.example.fefu_course.di

import com.example.fefu_course.data.repository.activity.LocalActivityDataSource
import com.example.fefu_course.data.repository.activity.MockActivityRepository
import com.example.fefu_course.data.repository.comment.CommentRepositoryImpl
import com.example.fefu_course.data.repository.comment.LocalCommentDataSource
import com.example.fefu_course.domain.ActivityRepository
import com.example.fefu_course.domain.CommentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideActivityRepository(
        localActivityDataSource: LocalActivityDataSource
    ): ActivityRepository {
        return MockActivityRepository(localActivityDataSource)
    }

    @Provides
    @Singleton
    fun provideCommentRepository(
        localCommentDataSource: LocalCommentDataSource
    ): CommentRepository {
        return CommentRepositoryImpl(localCommentDataSource)
    }
}
