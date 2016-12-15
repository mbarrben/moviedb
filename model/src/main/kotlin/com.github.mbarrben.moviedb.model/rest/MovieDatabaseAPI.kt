package com.github.mbarrben.moviedb.model.rest

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface MovieDatabaseAPI {

  companion object {
    val BASE_URL = "http://api.themoviedb.org/3/"
  }

  @GET("movie/popular") fun getPopularMovies(@Query("api_key") apiKey: String): Observable<PopularMoviesApiResponse>
}
