package com.github.mbarrben.moviedb.movies.di

import com.github.mbarrben.moviedb.BuildConfig
import com.github.mbarrben.moviedb.domain.movies.GetMovies
import com.github.mbarrben.moviedb.model.MovieRepository
import com.github.mbarrben.moviedb.model.rest.RestMovieRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import rx.Scheduler
import javax.inject.Named

@Module
class MoviesListModule {

  @Provides fun provideGetMovies(repo: MovieRepository, @Named("IO") subscribe: Scheduler, @Named("UI") observe: Scheduler): GetMovies {
    return GetMovies(repo, subscribe, observe)
  }

  @Provides fun provideMovieRepository(client: OkHttpClient): MovieRepository = RestMovieRepository(client, BuildConfig.API_KEY)
}
