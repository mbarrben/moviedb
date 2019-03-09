package com.github.mbarrben.moviedb.movies.data.network

import com.github.mbarrben.moviedb.movies.data.network.model.Dto
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import timber.log.Timber

interface MoviesDatabaseService {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val POSTER_PREFIX = "https://image.tmdb.org/t/p/w500"
    }

    @GET("movie/popular")
    fun popular(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): Call<Dto.MoviesResponse>

    @GET("search/movie")
    fun search(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): Call<Dto.MoviesResponse>

    @GET("movie/{id}")
    fun details(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String
    ): Call<Dto.Movie.Details>
}