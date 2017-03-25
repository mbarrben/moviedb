package com.github.mbarrben.moviedb.model.rest

import com.github.mbarrben.moviedb.model.entities.Movie
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDatabaseAPI {

  companion object {
    val BASE_URL = "http://api.themoviedb.org/3/"
  }

  @GET("movie/popular") fun popular(@Query("api_key") apiKey: String, @Query("page") page: Int = 1): Observable<PopularMoviesApiResponse>

  @GET("movie/{id}") fun details(@Path("id") id: Long, @Query("api_key") apiKey: String): Observable<Movie.Details>
}
