package com.github.mbarrben.moviedb.model.rest

import com.github.mbarrben.moviedb.model.entities.Movie
import com.google.gson.annotations.SerializedName

data class PopularMoviesApiResponse(
    @SerializedName("results") val results: List<Movie>,
    @SerializedName("page") val page: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)
