package com.github.mbarrben.moviedb.detail.data

import arrow.core.Either
import com.github.mbarrben.moviedb.detail.data.network.DetailApiClient
import com.github.mbarrben.moviedb.detail.data.network.model.Dto

class DetailRepository(
    private val apiClient: DetailApiClient,
    private val apiKey: String
) {
    fun detail(id: Long): Either<Dto.Error, Dto.Detail> = apiClient.detail(id, apiKey)
}