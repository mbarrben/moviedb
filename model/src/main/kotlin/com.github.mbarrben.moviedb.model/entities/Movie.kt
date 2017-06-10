package com.github.mbarrben.moviedb.model.entities

import java.util.Date

data class Movie(
    val id: Long,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val releaseDate: Date?,
    val originalLanguage: String,
    val voteCount: Int,
    val voteAverage: Float,
    private val posterPathLastSegment: String?,
    private val backdropPathLastSegment: String?
) {

  companion object {
    private val POSTER_PREFIX = "http://image.tmdb.org/t/p/w500"
  }

  val posterPath = POSTER_PREFIX + posterPathLastSegment
  val backdropPath = POSTER_PREFIX + backdropPathLastSegment

  data class List(val page: Int, val movies: kotlin.collections.List<Movie>) : kotlin.collections.List<Movie> by movies {

    companion object {
      val EMPTY = Movie.List(
          page = 1,
          movies = emptyList()
      )
    }

    operator fun plus(elements: Movie.List) = Movie.List(elements.page, movies + elements.movies)
  }

  data class Details(
      val budget: Int,
      val genres: kotlin.collections.List<Genre>,
      val homepage: String,
      val productionCompanies: kotlin.collections.List<Company>,
      val productionCountries: kotlin.collections.List<Country>,
      val spokenLanguages: kotlin.collections.List<Language>,
      val revenue: Int,
      val runtime: Int,
      val status: String,
      val tagline: String
  )
}

data class Genre(
    val id: Int,
    val name: String
)

data class Company(
    val id: Int,
    val name: String
)

data class Country(
    val iso: String,
    val name: String
)

data class Language(
    val iso: String,
    val name: String
)