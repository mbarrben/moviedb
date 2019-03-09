package com.github.mbarrben.moviedb.movies.data

import arrow.core.Either
import com.github.mbarrben.moviedb.BuildConfig
import com.github.mbarrben.moviedb.movies.data.network.MoviesDatabaseApiClient
import com.github.mbarrben.moviedb.movies.data.network.model.Dto

class MoviesRepository(
    private val apiClient: MoviesDatabaseApiClient,
    private val apiKey: String
) {
    fun popular(): Either<Dto.Error, List<Dto.Movie>> = apiClient.popular(apiKey)
        .map { it.movies }
}