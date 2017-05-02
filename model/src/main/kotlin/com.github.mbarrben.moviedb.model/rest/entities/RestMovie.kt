package com.github.mbarrben.moviedb.model.entities

import com.google.gson.annotations.SerializedName
import java.util.Date

data class RestMovie(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val releaseDate: Date?,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("vote_average") val voteAverage: Float,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?
) {

  data class RestDetails(
      @SerializedName("budget") val budget: Int,
      @SerializedName("genres") val genres: List<RestGenre>,
      @SerializedName("homepage") val homepage: String,
      @SerializedName("production_companies") val productionCompanies: List<RestCompany>,
      @SerializedName("production_countries") val productionCountries: List<RestCountry>,
      @SerializedName("spoken_languages") val spokenLanguages: List<RestLanguage>,
      @SerializedName("revenue") val revenue: Int,
      @SerializedName("runtime") val runtime: Int,
      @SerializedName("status") val status: String,
      @SerializedName("tagline") val tagline: String
  )
}

data class RestGenre(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)

data class RestCompany(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)

data class RestCountry(
    @SerializedName("iso_3166_1") val iso: String,
    @SerializedName("name") val name: String
)

data class RestLanguage(
    @SerializedName("iso_639_1") val iso: String,
    @SerializedName("name") val name: String
)