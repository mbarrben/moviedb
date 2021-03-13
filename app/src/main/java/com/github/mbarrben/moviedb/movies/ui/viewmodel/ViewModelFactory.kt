package com.github.mbarrben.moviedb.movies.ui.viewmodel

import android.content.Context
import android.widget.Toast
import com.github.mbarrben.moviedb.movies.domain.Movie
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    fun build(movie: Movie): MovieViewModel = MovieViewModel(
        id = movie.id,
        title = movie.title,
        posterPath = movie.posterPath,
        clickAction = { Toast.makeText(context, "${movie.title} clicked", Toast.LENGTH_SHORT).show() }
    )
}