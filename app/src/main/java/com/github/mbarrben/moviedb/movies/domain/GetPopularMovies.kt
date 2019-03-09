package com.github.mbarrben.moviedb.movies.domain

import arrow.core.Either
import com.github.mbarrben.moviedb.movies.data.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class GetPopularMovies(
    private val moviesRepository: MoviesRepository,
    private val context: CoroutineContext
) {
    suspend operator fun invoke(): Either<Error, List<Movie>> = withContext(context) {
        moviesRepository.popular()
            .mapLeft { Error }
            .map { movies -> movies.map { movie -> movie.toDomain() } }
    }
}