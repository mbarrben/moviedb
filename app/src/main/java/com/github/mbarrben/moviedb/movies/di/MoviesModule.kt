package com.github.mbarrben.moviedb.movies.di

import com.github.mbarrben.moviedb.di.NetworkModule
import com.github.mbarrben.moviedb.movies.data.MoviesRepository
import com.github.mbarrben.moviedb.movies.data.network.MoviesApiClient
import com.github.mbarrben.moviedb.movies.data.network.MoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object MoviesModule {
    @Provides
    fun provideMoviesRepository(apiClient: MoviesApiClient): MoviesRepository = MoviesRepository(
        apiClient = apiClient,
        apiKey = NetworkModule.API_KEY
    )

    @Provides
    fun provideMoviesService(retrofit: Retrofit): MoviesService = retrofit.create(MoviesService::class.java)
}