package com.github.mbarrben.moviedb.detail.ui.viewmodel

data class MovieDetailViewModel(
    val title: String,
    val originalTitle: String,
    val overview: String,
    val releaseDate: String?,
    val originalLanguage: String,
    val voteCount: String,
    val voteAverageText: String,
    val voteAverage: Float,
    val posterPath: String?,
    val backdropPath: String?,
    val budget: String?,
    val genres: String?,
    val homepage: String?,
    val productionCompanies: String?,
    val productionCountries: String?,
    val spokenLanguages: String?,
    val revenue: String?,
    val runtime: String?,
    val status: String?,
    val tagline: String?
)