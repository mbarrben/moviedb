package com.github.mbarrben.moviedb.movies.data.network

import com.github.mbarrben.moviedb.movies.data.network.model.Dto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("movie/popular")
    fun popular(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): Call<Dto.MoviesResponse>

    @GET("search/movie")
    fun search(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int = 1,
    ): Call<Dto.MoviesResponse>
}