package com.github.mbarrben.moviedb.model.entities

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class Movie(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") private val posterPathLastSegment: String
) {

  companion object {
    private val POSTER_PREFIX = "http://image.tmdb.org/t/p/w500"
  }

  fun posterPath() = POSTER_PREFIX + posterPathLastSegment

  class List : ArrayList<Movie>()
}
