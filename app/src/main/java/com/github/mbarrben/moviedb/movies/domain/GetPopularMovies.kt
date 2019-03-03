package com.github.mbarrben.moviedb.movies.domain

import arrow.core.Either
import arrow.instances.either.monad.binding
import com.github.mbarrben.moviedb.movies.data.MoviesRepository
import com.github.mbarrben.moviedb.movies.data.network.model.Dto

class GetPopularMovies(
    private val moviesRepository: MoviesRepository = MoviesRepository()
) {
    operator fun invoke(): Either<Dto.Error, List<Dto.Movie>> = binding {
        moviesRepository.popular().bind()
    }
}