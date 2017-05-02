package com.github.mbarrben.moviedb.model.rest

import com.github.mbarrben.moviedb.model.MovieRepository
import com.github.mbarrben.moviedb.model.entities.Movie
import com.github.mbarrben.moviedb.model.entities.Movie.Details
import com.github.mbarrben.moviedb.model.mappers.DetailsMapper
import com.github.mbarrben.moviedb.model.mappers.MovieMapper
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestMovieRepository(
    client: OkHttpClient,
    val apiKey: String,
    val gson: Gson,
    val movieMapper: MovieMapper,
    val detailsMapper: DetailsMapper
) : MovieRepository {

  private val api = Retrofit.Builder()
      .baseUrl(MovieDatabaseAPI.BASE_URL)
      .client(client)
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create(gson))
      .build()
      .create(MovieDatabaseAPI::class.java)

  override fun popular(page: Int): Observable<Movie.List> = api.popular(apiKey, page)
      .map { buildMovieList(it) }

  override fun search(query: String, page: Int): Observable<Movie.List> = api.search(apiKey, query, page)
      .map { buildMovieList(it) }

  private fun buildMovieList(response: PopularMoviesApiResponse) = Movie.List(
      response.page,
      response.results
          .filter { movie -> movie.releaseDate != null }
          .filter { movie -> movie.posterPath != null }
          .map { movieMapper.map(it) }
  )

  override fun details(id: Long): Observable<Details> = api.details(id, apiKey)
      .map { detailsMapper.map(it) }
}
