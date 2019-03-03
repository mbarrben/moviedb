package com.github.mbarrben.moviedb.movies.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

class Dto {

    sealed class Error {
        object NoResultFound : Error()
        object NoInternetConnection : Error()
    }

    @JsonClass(generateAdapter = true)
    data class MoviesResponse(
        @Json(name = "results") val movies: List<Movie>,
        @Json(name = "page") val page: Int,
        @Json(name = "total_pages") val totalPages: Int,
        @Json(name = "total_results") val totalMovies: Int
    )

    @JsonClass(generateAdapter = true)
    data class Movie(
        @Json(name = "id") val id: Long,
        @Json(name = "title") val title: String,
        @Json(name = "original_title") val originalTitle: String,
        @Json(name = "overview") val overview: String,
        @Json(name = "release_date") val releaseDate: Date?,
        @Json(name = "original_language") val originalLanguage: String,
        @Json(name = "vote_count") val voteCount: Int,
        @Json(name = "vote_average") val voteAverage: Float,
        @Json(name = "poster_path") val posterPath: ImageUrl?,
        @Json(name = "backdrop_path") val backdropPath: ImageUrl?
    ) {

        @JsonClass(generateAdapter = true)
        data class Details(
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
            @Json(name = "id") val id: Int,
            @Json(name = "name") val name: String
        )

        @JsonClass(generateAdapter = true)
        data class Company(
            @Json(name = "id") val id: Int,
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
}

data class ImageUrl(
    val url: String
)