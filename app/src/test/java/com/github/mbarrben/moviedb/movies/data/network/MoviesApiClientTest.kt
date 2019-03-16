package com.github.mbarrben.moviedb.movies.data.network

import arrow.core.left
import arrow.core.right
import com.github.mbarrben.moviedb.movies.data.DtoMother
import com.github.mbarrben.moviedb.movies.data.network.model.Dto
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class MoviesApiClientTest {

    private val serviceMock: MoviesService = mock()

    private val sut = MoviesApiClient(
        service = serviceMock
    )

    @Test
    fun `Returns an api response with a list of movies when the service returns a successful call`() {
        givenServiceReturns(ANY_SUCCESSFUL_CALL)

        val result = whenRetrievesPopularMovies()

        assertEquals(ANY_RESPONSE.right(), result)
    }

    @Test
    fun `Returns a not found error when the service returns an empty call`() {
        givenServiceReturns(ANY_EMPTY_SUCCESSFUL_CALL)

        val result = whenRetrievesPopularMovies()

        assertEquals(Dto.Error.NoResultFound.left(), result)
    }

    @Test
    fun `Returns a not found error when the service returns a not found error call`() {
        givenServiceReturns(ANY_NOT_FOUND_CALL)

        val result = whenRetrievesPopularMovies()

        assertEquals(Dto.Error.NoResultFound.left(), result)
    }

    @Test
    fun `Returns a no connection error when the service returns a no connection error call`() {
        givenServiceReturns(ANY_NO_CONNECTION_CALL)

        val result = whenRetrievesPopularMovies()

        assertEquals(Dto.Error.NoInternetConnection.left(), result)
    }

    private fun givenServiceReturns(call: Call<Dto.MoviesResponse>) {
        whenever(serviceMock.popular(API_KEY)).thenReturn(call)
    }

    private fun whenRetrievesPopularMovies() = sut.popular(API_KEY)

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