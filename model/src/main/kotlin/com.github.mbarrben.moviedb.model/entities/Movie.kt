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

  data class Details(
      @SerializedName("budget") val budget: Int,
      @SerializedName("genres") val genres: kotlin.collections.List<Genre>,
      @SerializedName("homepage") val homepage: String,
      @SerializedName("production_companies") val productionCompanies: kotlin.collections.List<Company>,
      @SerializedName("production_countries") val productionCountries: kotlin.collections.List<Country>,
      @SerializedName("spoken_languages") val spokenLanguages: kotlin.collections.List<Language>,
      @SerializedName("revenue") val revenue: Int,
      @SerializedName("runtime") val runtime: Int,
      @SerializedName("status") val status: String,
      @SerializedName("tagline") val tagline: String
  )
}

data class Genre(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)

data class Company(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)

data class Country(
    @SerializedName("iso_3166_1") val iso: String,
    @SerializedName("name") val name: String
)

data class Language(
    @SerializedName("iso_639_1") val iso: String,
    @SerializedName("name") val name: String
)