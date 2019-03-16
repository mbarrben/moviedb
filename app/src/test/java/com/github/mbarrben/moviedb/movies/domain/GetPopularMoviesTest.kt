package com.github.mbarrben.moviedb.movies.domain

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.github.mbarrben.moviedb.movies.data.DtoMother
import com.github.mbarrben.moviedb.movies.data.MoviesRepository
import com.github.mbarrben.moviedb.movies.data.network.model.Dto
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test


class GetPopularMoviesTest {
    private val repositoryMock: MoviesRepository = mock()

    private val sut = GetPopularMovies(
        moviesRepository = repositoryMock,
        context = Dispatchers.Default
    )

    @Test
    fun `Returns a list of movies when repository returns a list of movies`() {
        givenRepositoryReturns(ANY_DTO_MOVIE_LIST.right())

        val result = whenGetsPopularMovies()

        assertEquals(ANY_DOMAIN_MOVIE_LIST.right(), result)
    }

    @Test
    fun `Returns an error when repository returns a not found error`() {
        givenRepositoryReturns(Dto.Error.NoResultFound.left())

        val result = whenGetsPopularMovies()

        assertEquals(Error.left(), result)
    }

    @Test
    fun `Returns an error when repository returns a no connection error`() {
        givenRepositoryReturns(Dto.Error.NoInternetConnection.left())

        val result = whenGetsPopularMovies()

        assertEquals(Error.left(), result)
    }

    private fun givenRepositoryReturns(repositoryResult: Either<Dto.Error, List<Dto.Movie>>) {
        whenever(repositoryMock.popular()).thenReturn(repositoryResult)
    }

    private fun whenGetsPopularMovies() = runBlocking { sut() }

    private companion object {
        val ANY_DTO_MOVIE_LIST = listOf(
            DtoMother.aMovieDto(),
            DtoMother.aMovieDto(),
            DtoMother.aMovieDto(),
            DtoMother.aMovieDto()
        )

        val ANY_DOMAIN_MOVIE_LIST = listOf(
            DomainMother.aMovie(),
            DomainMother.aMovie(),
            DomainMother.aMovie(),
            DomainMother.aMovie()
        )
    }
}