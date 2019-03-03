package com.github.mbarrben.moviedb.movies.viewmodel

import com.github.mbarrben.moviedb.movies.data.network.model.Dto
import timber.log.Timber

class ViewModelFactory {
    fun build(dto: Dto.Movie): MovieViewModel = MovieViewModel(
        id = dto.id,
        title = dto.title,
        posterPath = dto.posterPath?.url,
        clickAction = { Timber.tag("click").d("click on ${dto.title}") }
    )
}