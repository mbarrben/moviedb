package com.github.mbarrben.moviedb.model.rest

import retrofit.http.GET
import retrofit.http.Query
import rx.Observable

interface MovieDatabaseAPI {
  @GET("/movie/popular") fun getPopularMovies(@Query("api_key") apiKey: String): Observable<PopularMoviesApiResponse>
}
