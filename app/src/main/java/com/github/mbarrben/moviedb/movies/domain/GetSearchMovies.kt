package com.github.mbarrben.moviedb.movies.domain

import arrow.core.Either
import com.github.mbarrben.moviedb.di.IO
import com.github.mbarrben.moviedb.movies.data.MoviesRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class GetSearchMovies @Inject constructor(
    private val moviesRepository: MoviesRepository,
    @IO private val context: CoroutineContext,
) {
    suspend operator fun invoke(query: String, page: Int): Either<Error, Content> = withContext(context) {
        moviesRepository.search(query, page)
            .mapLeft { Error }
            .map { response -> response.toDomain() }
    }
}