package com.github.mbarrben.moviedb.movies.data.network

import com.github.mbarrben.moviedb.movies.data.DtoMother
import com.github.mbarrben.moviedb.movies.data.network.model.Dto
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class MoviesApiClientTest {

    private val serviceMock: MoviesService = mock()

    private val sut = MoviesApiClient(
        service = serviceMock
    )

    @Test
    fun `Returns an api response with a list of movies when the service returns a successful call`() {
        whenever(serviceMock.popular(API_KEY)).thenReturn(ANY_SUCCESSFUL_CALL)

        sut.popular(API_KEY)
            .fold(
                ifLeft = { fail() },
                ifRight = { result -> assertEquals(ANY_RESPONSE, result) }
            )
    }

    @Test
    fun `Returns a not found error when the service returns an empty call`() {
        whenever(serviceMock.popular(API_KEY)).thenReturn(ANY_EMPTY_SUCCESSFUL_CALL)

        sut.popular(API_KEY)
            .fold(
                ifLeft = { error -> assertEquals(ANY_NOT_FOUND_ERROR, error) },
                ifRight = { fail() }
            )
    }

    @Test
    fun `Returns a not found error when the service returns a not found error call`() {
        whenever(serviceMock.popular(API_KEY)).thenReturn(ANY_NOT_FOUND_CALL)

        sut.popular(API_KEY)
            .fold(
                ifLeft = { error -> assertEquals(ANY_NOT_FOUND_ERROR, error) },
                ifRight = { fail() }
            )
    }

    @Test
    fun `Returns a no connection error when the service returns a no connection error call`() {
        whenever(serviceMock.popular(API_KEY)).thenReturn(ANY_NO_CONNECTION_CALL)

        sut.popular(API_KEY)
            .fold(
                ifLeft = { error -> assertEquals(ANY_NO_CONNECTION_ERROR, error) },
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

        val ANY_NOT_FOUND_ERROR = Dto.Error.NoResultFound
        val ANY_NO_CONNECTION_ERROR = Dto.Error.NoInternetConnection

        val ANY_SUCCESSFUL_CALL = CallMother.aCall {
            Response.success(ANY_RESPONSE)
        }

        val ANY_EMPTY_SUCCESSFUL_CALL = CallMother.aCall {
            Response.success<Dto.MoviesResponse>(null)
        }

        val ANY_NOT_FOUND_CALL = CallMother.aCall {
            Response.error<Dto.MoviesResponse>(404, mock())
        }

        val ANY_NO_CONNECTION_CALL = CallMother.aCall<Dto.MoviesResponse> {
            throw IOException("No internet connection")
        }
    }
}