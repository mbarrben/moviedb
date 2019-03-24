package com.github.mbarrben.moviedb.detail.data

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_ID
import com.github.mbarrben.moviedb.detail.data.network.DetailApiClient
import com.github.mbarrben.moviedb.detail.data.network.model.Dto
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.*
import org.junit.Test

class DetailRepositoryTest {
    private val apiClientMock: DetailApiClient = mock()

    private val sut = DetailRepository(
        apiClient = apiClientMock,
        apiKey = API_KEY
    )

    @Test
    fun `Returns a movie detail when api client call succeeds`() {
        givenApiClientReturns(ANY_MOVIE_DETAIL.right())

        val result = whenRetrievesMovieDetail()

        assertEquals(ANY_MOVIE_DETAIL.right(), result)
    }

    @Test
    fun `Returns an error when api client call fails`() {
        givenApiClientReturns(ANY_ERROR.left())

        val result = whenRetrievesMovieDetail()

        assertEquals(ANY_ERROR.left(), result)
    }

    private fun givenApiClientReturns(apiResult: Either<Dto.Error, Dto.Detail>) {
        whenever(apiClientMock.detail(ANY_ID, API_KEY)).thenReturn(apiResult)
    }

    private fun whenRetrievesMovieDetail() = sut.detail(ANY_ID)

    private companion object {
        const val API_KEY = "api key"

        val ANY_MOVIE_DETAIL = DtoMother.aDetail()
        val ANY_ERROR = Dto.Error.NoResultFound
    }
}