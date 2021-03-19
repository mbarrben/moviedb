package com.github.mbarrben.moviedb.movies.ui.viewmodel

import android.widget.Toast
import coil.ImageLoader
import com.github.mbarrben.moviedb.movies.domain.Movie
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val imageLoader: ImageLoader,
) {
    fun build(movie: Movie): MovieViewModel = MovieViewModel(
        id = movie.id,
        title = movie.title,
        posterPath = movie.posterPath,
        imageLoader = imageLoader,
        clickAction = { context -> Toast.makeText(context, "${movie.title} clicked", Toast.LENGTH_SHORT).show() },
    )
}