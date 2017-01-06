package com.github.mbarrben.moviedb.model.entities

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Movie(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val releaseDate: Date,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("genre_ids") val genreIds: kotlin.collections.List<Long>,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("vote_average") val voteAverage: Float,
    @SerializedName("poster_path") private val posterPathLastSegment: String,
    @SerializedName("backdrop_path") private val backdropPathLastSegment: String
) {

  companion object {
    private val POSTER_PREFIX = "http://image.tmdb.org/t/p/w500"
  }

  fun posterPath() = POSTER_PREFIX + posterPathLastSegment

  fun backdropPath() = POSTER_PREFIX + backdropPathLastSegment

  data class List(val page: Int, val movies: kotlin.collections.List<Movie>) : kotlin.collections.List<Movie> by movies {
    operator fun plus(elements: Movie.List) = Movie.List(elements.page, movies + elements.movies)
  }
}
