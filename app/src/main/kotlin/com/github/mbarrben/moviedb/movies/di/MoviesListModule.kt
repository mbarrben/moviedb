package com.github.mbarrben.moviedb.movies.di

import com.github.mbarrben.moviedb.BuildConfig
import com.github.mbarrben.moviedb.domain.movies.GetMovies
import com.github.mbarrben.moviedb.extensions.Timber
import com.github.mbarrben.moviedb.extensions.d
import com.github.mbarrben.moviedb.model.MovieRepository
import com.github.mbarrben.moviedb.model.rest.NetworkClient
import com.github.mbarrben.moviedb.model.rest.RestMovieRepository
import dagger.Module
import dagger.Provides
import rx.Scheduler
import javax.inject.Named

@Module
class MoviesListModule {
  @Provides fun provideGetMovies(repo: MovieRepository, @Named("IO") subscribe: Scheduler, @Named("UI") observe: Scheduler): GetMovies {
    return GetMovies(repo, subscribe, observe)
  }

  @Provides fun provideMovieRepository(): MovieRepository = RestMovieRepository(
      NetworkClient.create({ message -> Timber.tag("Retrofit").d { message } }),
      BuildConfig.API_KEY
  )
}
