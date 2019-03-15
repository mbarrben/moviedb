package com.github.mbarrben.moviedb.movies.data

import arrow.core.left
import arrow.core.right
import com.github.mbarrben.moviedb.movies.data.network.MoviesApiClient
import com.github.mbarrben.moviedb.movies.data.network.model.Dto
import com.github.mbarrben.moviedb.movies.data.network.model.ImageUrl
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test
import java.util.Date

class MoviesRepositoryTest {

    private val apiClientMock: MoviesApiClient = mock()

    private val sut = MoviesRepository(
        apiClient = apiClientMock,
        apiKey = API_KEY
    )

    @Test
    fun `Returns a list of movies when api client call succeeds`() {
        val expected = ANY_RESPONSE.movies
        whenever(apiClientMock.popular(API_KEY)).thenReturn(ANY_RESPONSE.right())

        sut.popular()
            .fold(
                ifLeft = { fail() },
                ifRight = { result -> assertEquals(expected, result) }
            )
    }

    @Test
    fun `Returns an error when api client call fails`() {
        whenever(apiClientMock.popular(API_KEY)).thenReturn(ANY_ERROR.left())

        sut.popular()
            .fold(
                ifLeft = { result -> assertEquals(ANY_ERROR, result) },
                ifRight = { fail() }
            )
    }

    private companion object {
        const val API_KEY = "api key"

        val ANY_MOVIE = DtoMother.aMovieDto()

        val ANY_RESPONSE = Dto.MoviesResponse(
            movies = listOf(
                ANY_MOVIE,
                ANY_MOVIE,
                ANY_MOVIE,
                ANY_MOVIE,
                ANY_MOVIE,
                ANY_MOVIE
            ),
            page = 1,
            totalPages = 123,
            totalMovies = 14092
        )

        val ANY_ERROR = Dto.Error.NoInternetConnection
    }
}