package com.github.mbarrben.moviedb.movies.ui.viewmodel

import android.content.Context
import coil.ImageLoader

data class MovieViewModel(
    val id: Long,
    val title: String,
    val posterPath: String?,
    val imageLoader: ImageLoader,
    val clickAction: (Context) -> Unit,
)
