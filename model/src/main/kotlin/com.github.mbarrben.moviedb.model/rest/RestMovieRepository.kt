package com.github.mbarrben.moviedb.model.rest

import com.github.mbarrben.moviedb.model.MovieRepository
import com.github.mbarrben.moviedb.model.entities.Movie
import com.github.mbarrben.moviedb.model.entities.Movie.List
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestMovieRepository(client: OkHttpClient, val apiKey: String, val gson: Gson) : MovieRepository {

  private val api = Retrofit.Builder()
      .baseUrl(MovieDatabaseAPI.BASE_URL)
      .client(client)
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create(gson))
      .build()
      .create(MovieDatabaseAPI::class.java)

  override fun popular(page: Int): Observable<List> = api.popular(apiKey, page)
      .map { Movie.List(it.page, it.results) }

  override fun details(id: Long) = api.details(id, apiKey)
}
