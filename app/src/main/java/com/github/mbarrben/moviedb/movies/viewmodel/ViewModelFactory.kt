package com.github.mbarrben.moviedb.movies.viewmodel

import com.github.mbarrben.moviedb.movies.data.network.model.Dto
import com.github.mbarrben.moviedb.movies.domain.NavigateToDetail

class ViewModelFactory(
    private val navigateToDetail: NavigateToDetail = NavigateToDetail()
) {
    fun build(dto: Dto.Movie): MovieViewModel = MovieViewModel(
        id = dto.id,
        title = dto.title,
        posterPath = dto.posterPath?.url,
        clickAction = { navigateToDetail(it) }
    )
}