package com.github.mbarrben.moviedb.movies.data

import arrow.core.Either
import com.github.mbarrben.moviedb.movies.data.network.MoviesApiClient
import com.github.mbarrben.moviedb.movies.data.network.model.Dto
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val apiClient: MoviesApiClient,
    private val apiKey: String
) {
    fun popular(page: Int): Either<Dto.Error, List<Dto.Movie>> = apiClient
        .popular(apiKey, page)
        .map { it.movies }
}