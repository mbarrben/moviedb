package com.github.mbarrben.moviedb.movies.di

import com.github.mbarrben.moviedb.domain.movies.GetMovies
import com.github.mbarrben.moviedb.model.MovieRepository
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named

@Module
class MoviesListModule {

  @Provides fun getMovies(
      repo: MovieRepository,
      @Named("IO") subscribe: Scheduler,
      @Named("UI") observe: Scheduler
  ): GetMovies = GetMovies(repo, subscribe, observe)

}
