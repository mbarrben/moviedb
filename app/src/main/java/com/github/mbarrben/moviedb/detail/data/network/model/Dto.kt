package com.github.mbarrben.moviedb.detail.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

object Dto {

    sealed class Error {
        object NoResultFound : Error()
        object NoInternetConnection : Error()
    }

    @JsonClass(generateAdapter = true)
    data class Detail(
        @Json(name = "budget") val budget: Int,
        @Json(name = "genres") val genres: List<Genre>,
        @Json(name = "homepage") val homepage: String,
        @Json(name = "production_companies") val productionCompanies: List<Company>,
        @Json(name = "production_countries") val productionCountries: List<Country>,
        @Json(name = "spoken_languages") val spokenLanguages: List<Language>,
        @Json(name = "revenue") val revenue: Int,
        @Json(name = "runtime") val runtime: Int,
        @Json(name = "status") val status: String,
        @Json(name = "tagline") val tagline: String
    )

    @JsonClass(generateAdapter = true)
    data class Genre(
        @Json(name = "id") val id: Long,
        @Json(name = "name") val name: String
    )

    @JsonClass(generateAdapter = true)
    data class Company(
        @Json(name = "id") val id: Long,
        @Json(name = "name") val name: String
    )

    @JsonClass(generateAdapter = true)
    data class Country(
        @Json(name = "iso_3166_1") val iso: String,
        @Json(name = "name") val name: String
    )

    @JsonClass(generateAdapter = true)
    data class Language(
        @Json(name = "iso_639_1") val iso: String,
        @Json(name = "name") val name: String
    )
}
