package com.github.mbarrben.moviedb.movies.di

import com.github.mbarrben.moviedb.domain.movies.GetMovies
import com.github.mbarrben.moviedb.domain.movies.PopularMovies
import com.github.mbarrben.moviedb.domain.movies.SearchMovies
import com.github.mbarrben.moviedb.model.MovieRepository
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named

@Module
class MoviesListModule {

  @Provides fun getMovies(popular: PopularMovies, search: SearchMovies): GetMovies = GetMovies(popular, search)

  @Provides fun popularMovies(
      repo: MovieRepository,
      @Named("IO") subscribe: Scheduler,
      @Named("UI") observe: Scheduler
  ): PopularMovies = PopularMovies(repo, subscribe, observe)

  @Provides fun searchMovies(
      repo: MovieRepository,
      @Named("IO") subscribe: Scheduler,
      @Named("UI") observe: Scheduler
  ): SearchMovies = SearchMovies(repo, subscribe, observe)

}
