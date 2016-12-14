package com.github.mbarrben.moviedb.movies.di

import com.github.mbarrben.moviedb.BuildConfig
import com.github.mbarrben.moviedb.di.IOScheduler
import com.github.mbarrben.moviedb.di.UIScheduler
import com.github.mbarrben.moviedb.domain.movies.GetMovies
import com.github.mbarrben.moviedb.model.MovieRepository
import com.github.mbarrben.moviedb.model.rest.RestMovieRepository
import dagger.Module
import dagger.Provides
import rx.Scheduler

@Module
class MoviesListModule {
  @Provides fun provideGetMovies(repo: MovieRepository, @IOScheduler subscribe: Scheduler, @UIScheduler observe: Scheduler): GetMovies {
    return GetMovies(repo, subscribe, observe)
  }

  @Provides fun provideMovieRepository(): MovieRepository = RestMovieRepository(BuildConfig.DEBUG, BuildConfig.API_KEY)
}
