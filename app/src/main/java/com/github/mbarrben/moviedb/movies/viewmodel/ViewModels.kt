package com.github.mbarrben.moviedb.movies.viewmodel

import android.view.View

data class MovieViewModel(
    val id: Long,
    val title: String,
    val posterPath: String?,
    val clickAction: (View) -> Unit
)
