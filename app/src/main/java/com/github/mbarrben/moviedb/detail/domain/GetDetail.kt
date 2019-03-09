package com.github.mbarrben.moviedb.detail.domain

import arrow.core.Either
import com.github.mbarrben.moviedb.detail.data.DetailRepository
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class GetDetail(
    private val repository: DetailRepository,
    private val context: CoroutineContext
) {
    suspend operator fun invoke(id: Long): Either<Error, Detail> = withContext(context) {
        repository.detail(id)
            .mapLeft { Error }
    }
}