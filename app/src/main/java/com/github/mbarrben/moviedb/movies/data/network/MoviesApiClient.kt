package com.github.mbarrben.moviedb.movies.data.network

import arrow.core.Either
import com.github.mbarrben.moviedb.commons.network.toEither
import com.github.mbarrben.moviedb.movies.data.network.model.Dto
import javax.inject.Inject

class MoviesApiClient @Inject constructor(
    private val service: MoviesService,
) {
    fun popular(apiKey: String, page: Int): Either<Dto.Error, Dto.MoviesResponse> = service
        .popular(apiKey, page)
        .toEither(
            notFoundError = Dto.Error.NoResultFound,
            connectionError = Dto.Error.NoInternetConnection,
        )

    fun search(apiKey: String, query: String, page: Int): Either<Dto.Error, Dto.MoviesResponse> = service
        .search(apiKey, query, page)
        .toEither(
            notFoundError = Dto.Error.NoResultFound,
            connectionError = Dto.Error.NoInternetConnection,
        )
}