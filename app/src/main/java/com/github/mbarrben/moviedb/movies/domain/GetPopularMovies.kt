package com.github.mbarrben.moviedb.movies.domain

import arrow.core.Either
import arrow.instances.either.monad.binding
import com.github.mbarrben.moviedb.movies.data.MoviesRepository

class GetPopularMovies(
    private val moviesRepository: MoviesRepository = MoviesRepository()
) {
    operator fun invoke(): Either<Error, List<Movie>> = binding {
        moviesRepository.popular()
            .mapLeft { Error }
            .map { movies -> movies.map { movie -> movie.toDomain() } }
            .bind()
    }
}