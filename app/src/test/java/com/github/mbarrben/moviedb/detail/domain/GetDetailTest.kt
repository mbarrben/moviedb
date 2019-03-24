package com.github.mbarrben.moviedb.detail.domain

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_ID
import com.github.mbarrben.moviedb.detail.data.DetailRepository
import com.github.mbarrben.moviedb.detail.data.DtoMother
import com.github.mbarrben.moviedb.detail.data.network.model.Dto
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class GetDetailTest {
    private val repositoryMock: DetailRepository = mock()

    private val sut = GetDetail(
        repository = repositoryMock,
        context = Dispatchers.Unconfined
    )

    @Test
    fun `Returns a movie detail when repository returns a movie detail`() {
        givenRepositoryReturns(ANY_DTO_DETAIL.right())

        val result = whenGetsPopularMovies()

        assertEquals(ANY_DOMAIN_DETAIL.right(), result)
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

    private fun givenRepositoryReturns(repositoryResult: Either<Dto.Error, Dto.Detail>) {
        whenever(repositoryMock.detail(ANY_ID)).thenReturn(repositoryResult)
    }

    private fun whenGetsPopularMovies() = runBlocking { sut(ANY_ID) }

    private companion object {
        val ANY_DTO_DETAIL = DtoMother.aDetail()
        val ANY_DOMAIN_DETAIL = DomainMother.aDetail()
    }
}