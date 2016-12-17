package com.github.mbarrben.moviedb.model.entities

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") private val posterPathLastSegment: String
) {

  companion object {
    private val POSTER_PREFIX = "http://image.tmdb.org/t/p/w500"
  }

  fun posterPath() = POSTER_PREFIX + posterPathLastSegment

  data class List(val page: Int, val movies: kotlin.collections.List<Movie>) : kotlin.collections.List<Movie> by movies {
    operator fun plus(elements: Movie.List) = Movie.List(elements.page, movies + elements.movies)
  }
}
