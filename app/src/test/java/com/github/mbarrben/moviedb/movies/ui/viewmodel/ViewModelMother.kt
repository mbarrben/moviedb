package com.github.mbarrben.moviedb.movies.ui.viewmodel

import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_ID
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_POSTER_PATH
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_TITLE
import com.github.mbarrben.moviedb.movies.domain.DomainMother

internal class ViewModelMother {
    companion object {
        fun aMovie() = MovieViewModel(
            id = ANY_ID,
            title = ANY_TITLE,
            posterPath = ANY_POSTER_PATH,
            model = DomainMother.aMovie(),
        )
    }
}