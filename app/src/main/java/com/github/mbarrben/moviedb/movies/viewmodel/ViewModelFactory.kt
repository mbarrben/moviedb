package com.github.mbarrben.moviedb.movies.viewmodel

import com.github.mbarrben.moviedb.movies.domain.Movie
import com.github.mbarrben.moviedb.movies.domain.NavigateToDetail

class ViewModelFactory(
    private val navigateToDetail: NavigateToDetail
) {
    fun build(movie: Movie): MovieViewModel = MovieViewModel(
        id = movie.id,
        title = movie.title,
        posterPath = movie.posterPath,
        clickAction = { view -> navigateToDetail(view, movie) }
    )
}