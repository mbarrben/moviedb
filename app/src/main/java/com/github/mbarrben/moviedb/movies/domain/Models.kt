package com.github.mbarrben.moviedb.movies.domain

import android.os.Parcelable
import com.github.mbarrben.moviedb.movies.data.network.model.Dto
import java.util.Date
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Long,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val releaseDate: Date?,
    val originalLanguage: String,
    val voteCount: Int,
    val voteAverage: Float,
    val posterPath: String?,
    val backdropPath: String?
) : Parcelable

object Error

fun Dto.Movie.toDomain() = Movie(
    id = id,
    title = title,
    originalTitle = originalTitle,
    overview = overview,
    releaseDate = releaseDate,
    originalLanguage = originalLanguage,
    voteCount = voteCount,
    voteAverage = voteAverage,
    posterPath = posterPath?.url,
    backdropPath = backdropPath?.url
)