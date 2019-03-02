package com.github.mbarrben.moviedb.network

import arrow.core.Either
import com.github.mbarrben.moviedb.network.model.Dto


class MoviesDatabaseApiClient(
    private val service: MoviesDatabaseService = MoviesDatabaseService.Provider.create()
) {
    fun popular(apiKey: String): Either<Dto.Error, Dto.MoviesResponse> = service.popular(apiKey).toEither(
        notFoundError = Dto.Error.NoResultFound,
        connectionError = Dto.Error.NoInternetConnection
    )
}