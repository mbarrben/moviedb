package com.github.mbarrben.moviedb.detail.data.network

import arrow.core.Either
import com.github.mbarrben.moviedb.commons.network.toEither
import com.github.mbarrben.moviedb.detail.data.network.model.Dto


class DetailApiClient(
    private val service: DetailService
) {
    fun detail(id: Long, apiKey: String): Either<Dto.Error, Dto.Detail> = service.detail(id, apiKey).toEither(
        notFoundError = Dto.Error.NoResultFound,
        connectionError = Dto.Error.NoInternetConnection
    )
}