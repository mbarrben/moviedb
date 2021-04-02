package com.github.mbarrben.moviedb.movies.data

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.github.mbarrben.moviedb.movies.data.network.MoviesApiClient
import com.github.mbarrben.moviedb.movies.data.network.model.Dto
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Test

class MoviesRepositoryTest {

    private val apiClientMock: MoviesApiClient = mock()

    private val sut = MoviesRepository(
        apiClient = apiClientMock,
        apiKey = API_KEY
    )

    @Test
    fun `Returns a list of movies when api client call succeeds`() {
        givenApiClientReturns(ANY_RESPONSE.right())

        val result = whenRetrievesPopularMovies()

        assertEquals(ANY_MOVIE_LIST.right(), result)
    }

    @Test
    fun `Returns an error when api client call fails`() {
        givenApiClientReturns(ANY_ERROR.left())

        val result = whenRetrievesPopularMovies()

        assertEquals(ANY_ERROR.left(), result)
    }

    private fun givenApiClientReturns(apiResult: Either<Dto.Error, Dto.MoviesResponse>) {
        whenever(apiClientMock.popular(API_KEY, ANY_PAGE)).thenReturn(apiResult)
    }

    private fun whenRetrievesPopularMovies() = sut.popular(ANY_PAGE)

    private companion object {
        const val API_KEY = "api key"
        const val ANY_PAGE = 4

        val ANY_MOVIE_LIST = listOf(
            DtoMother.aMovieDto(),
            DtoMother.aMovieDto(),
            DtoMother.aMovieDto(),
            DtoMother.aMovieDto(),
            DtoMother.aMovieDto(),
            DtoMother.aMovieDto()
        )

        val ANY_RESPONSE = Dto.MoviesResponse(
            movies = ANY_MOVIE_LIST,
            page = 1,
            totalPages = 123,
            totalMovies = 14092
        )

        val ANY_ERROR = Dto.Error.NoInternetConnection
    }
}