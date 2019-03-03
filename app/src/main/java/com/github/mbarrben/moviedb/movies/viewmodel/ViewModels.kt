package com.github.mbarrben.moviedb.movies.viewmodel

data class MovieViewModel(
    val id: Long,
    val title: String,
    val posterPath: String?,
    val clickAction: () -> Unit
)
