package com.github.mbarrben.moviedb.detail.data.network

import com.github.mbarrben.moviedb.detail.data.network.model.Dto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailService {

    @GET("movie/{id}")
    fun detail(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String
    ): Call<Dto.Detail>
}