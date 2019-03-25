package com.github.mbarrben.moviedb.movies.data.network

import arrow.core.Either
import com.github.mbarrben.moviedb.commons.network.toEither
import com.github.mbarrben.moviedb.movies.data.network.model.Dto


class MoviesApiClient(
    private val service: MoviesService
) {
    fun popular(apiKey: String, page: Int): Either<Dto.Error, Dto.MoviesResponse> = service.popular(apiKey, page)
        .toEither(
            notFoundError = Dto.Error.NoResultFound,
            connectionError = Dto.Error.NoInternetConnection
        )
}