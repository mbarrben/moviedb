package com.github.mbarrben.moviedb.model.rest

import com.github.mbarrben.moviedb.model.MovieRepository
import com.github.mbarrben.moviedb.model.entities.Movie
import retrofit.RestAdapter.Builder
import retrofit.RestAdapter.LogLevel.FULL
import retrofit.RestAdapter.LogLevel.NONE
import rx.Observable

class RestMovieRepository(debug: Boolean, val apiKey: String) : MovieRepository {

  companion object {
    private val MOVIE_DB_HOST = "http://api.themoviedb.org/3/"
  }

  private val api = Builder()
      .setEndpoint(MOVIE_DB_HOST)
      .setLogLevel(if (debug) FULL else NONE)
      .build()
      .create(MovieDatabaseAPI::class.java)

  override fun getMovies(): Observable<Movie.List> = api.getPopularMovies(apiKey)
      .map { it.results }
}
