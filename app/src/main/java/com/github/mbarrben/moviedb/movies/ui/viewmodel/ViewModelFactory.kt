package com.github.mbarrben.moviedb.movies.ui.viewmodel

import coil.ImageLoader
import com.github.mbarrben.moviedb.movies.domain.Movie
import javax.inject.Inject

class ViewModelFactory @Inject constructor() {
    fun build(movie: Movie): MovieViewModel = MovieViewModel(
        id = movie.id,
        title = movie.title,
        posterPath = movie.posterPath,
        model = movie,
    )
}