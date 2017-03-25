package com.github.mbarrben.moviedb.movies.di

import com.github.mbarrben.moviedb.domain.moviesDetail.GetDetails
import com.github.mbarrben.moviedb.model.MovieRepository
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named

@Module
class MovieDetailModule {

  @Provides fun getDetails(
      repo: MovieRepository,
      @Named("IO") subscribe: Scheduler,
      @Named("UI") observe: Scheduler
  ): GetDetails = GetDetails(repo, subscribe, observe)

}
