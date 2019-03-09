package com.github.mbarrben.moviedb.movies.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

object Dto {

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
    )
}

data class ImageUrl(
    val url: String
)