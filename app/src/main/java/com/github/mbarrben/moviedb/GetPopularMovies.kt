package com.github.mbarrben.moviedb

import arrow.core.Either
import arrow.instances.either.monad.binding
import com.github.mbarrben.moviedb.network.model.Dto

class GetPopularMovies(
    private val moviesRepository: MoviesRepository = MoviesRepository()
) {
    operator fun invoke(): Either<Dto.Error, List<Dto.Movie>> = binding {
        moviesRepository.popular().bind()
    }
}