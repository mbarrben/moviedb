package com.github.mbarrben.moviedb.movies.ui.viewmodel

import coil.ImageLoader
import com.github.mbarrben.moviedb.movies.domain.Movie

data class MovieViewModel(
    val id: Long,
    val title: String,
    val posterPath: String?,
    val model: Movie,
)
