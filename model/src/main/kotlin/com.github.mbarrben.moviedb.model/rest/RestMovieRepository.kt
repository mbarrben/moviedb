package com.github.mbarrben.moviedb.model.rest

import com.github.mbarrben.moviedb.model.MovieRepository
import com.github.mbarrben.moviedb.model.entities.Movie
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

class RestMovieRepository(client: OkHttpClient, val apiKey: String) : MovieRepository {

  private val api = Retrofit.Builder()
      .baseUrl(MovieDatabaseAPI.BASE_URL)
      .client(client)
      .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(MovieDatabaseAPI::class.java)

  override fun getMovies(): Observable<Movie.List> = api.getPopularMovies(apiKey)
      .map { it.results }
}
