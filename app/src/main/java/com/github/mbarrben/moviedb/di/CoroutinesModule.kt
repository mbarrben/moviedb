package com.github.mbarrben.moviedb.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import kotlin.coroutines.CoroutineContext

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IO

@Module
@InstallIn(SingletonComponent::class)
object CoroutinesModule {

    @IO
    @Provides
    fun provideCoroutineContext(): CoroutineContext = Dispatchers.IO
}