package com.github.mbarrben.moviedb.detail.data.network

import arrow.core.left
import arrow.core.right
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_ID
import com.github.mbarrben.moviedb.commons.network.CallMother
import com.github.mbarrben.moviedb.detail.data.DtoMother
import com.github.mbarrben.moviedb.detail.data.network.model.Dto
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class DetailApiClientTest {
    private val serviceMock: DetailService = mock()

    private val sut = DetailApiClient(
        service = serviceMock
    )

    @Test
    fun `Returns an api response with a movie detail when the service returns a successful call`() {
        givenServiceReturns(ANY_SUCCESSFUL_CALL)

        val result = whenRetrievesDetail()

        assertEquals(ANY_MOVIE_DETAIL.right(), result)
    }

    @Test
    fun `Returns a not found error when the service returns an empty call`() {
        givenServiceReturns(ANY_EMPTY_SUCCESSFUL_CALL)

        val result = whenRetrievesDetail()

        assertEquals(Dto.Error.NoResultFound.left(), result)
    }

    @Test
    fun `Returns a not found error when the service returns a not found error call`() {
        givenServiceReturns(ANY_NOT_FOUND_CALL)

        val result = whenRetrievesDetail()

        assertEquals(Dto.Error.NoResultFound.left(), result)
    }

    @Test
    fun `Returns a no connection error when the service returns a no connection error call`() {
        givenServiceReturns(ANY_NO_CONNECTION_CALL)

        val result = whenRetrievesDetail()

        assertEquals(Dto.Error.NoInternetConnection.left(), result)
    }

    private fun givenServiceReturns(call: Call<Dto.Detail>) {
        whenever(
            serviceMock.detail(
                id = ANY_ID,
                apiKey = API_KEY
            )
        ).thenReturn(call)
    }

    private fun whenRetrievesDetail() = sut.detail(
        id = ANY_ID,
        apiKey = API_KEY
    )

    private companion object {
        const val API_KEY = "api key"

        val ANY_MOVIE_DETAIL = DtoMother.aDetail()

        val ANY_SUCCESSFUL_CALL = CallMother.aCall {
            Response.success(ANY_MOVIE_DETAIL)
        }

        val ANY_EMPTY_SUCCESSFUL_CALL = CallMother.aCall {
            Response.success<Dto.Detail>(null)
        }

        val ANY_NOT_FOUND_CALL = CallMother.aCall {
            Response.error<Dto.Detail>(404, mock())
        }

        val ANY_NO_CONNECTION_CALL = CallMother.aCall<Dto.Detail> {
            throw IOException("No internet connection")
        }
    }
}