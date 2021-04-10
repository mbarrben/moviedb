package com.github.mbarrben.moviedb.navigation.di

import com.github.mbarrben.moviedb.navigation.NavigationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {

    @Singleton
    @Provides
    fun providesNavigationManager() = NavigationManager()
}