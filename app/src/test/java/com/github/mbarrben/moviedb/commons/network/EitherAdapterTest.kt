package com.github.mbarrben.moviedb.commons.network

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class EitherAdapterTest {
    private enum class Error {
        NotFound,
        NoConnection
    }

    @Test
    fun `Returns a right string when the call is successful`() {
        whenAdapts(ANY_SUCCESSFUL_CALL) thenResultIs ANY_STRING.right()
    }

    @Test
    fun `Returns a not found error when the call is empty`() {
        whenAdapts(ANY_EMPTY_SUCCESSFUL_CALL) thenResultIs Error.NotFound.left()
    }

    @Test
    fun `Returns a not found error when the call returns an error 404`() {
        whenAdapts(ANY_NOT_FOUND_CALL) thenResultIs Error.NotFound.left()
    }

    @Test
    fun `Returns a no connection error when the call throws an IOException`() {
        whenAdapts(ANY_NO_CONNECTION_CALL) thenResultIs Error.NoConnection.left()
    }

    private fun whenAdapts(call: Call<String>) = call.toEither(Error.NotFound, Error.NoConnection)

    private infix fun Either<Error, String>.thenResultIs(expected: Either<Error, String>) {
        assertEquals(expected, this)
    }

    private companion object {
        const val ANY_STRING = "any_string"

        val ANY_SUCCESSFUL_CALL = CallMother.aCall {
            Response.success(ANY_STRING)
        }

        val ANY_EMPTY_SUCCESSFUL_CALL = CallMother.aCall {
            Response.success<String>(null)
        }

        val ANY_NOT_FOUND_CALL = CallMother.aCall {
            Response.error<String>(404, mock())
        }

        val ANY_NO_CONNECTION_CALL = CallMother.aCall<String> {
            throw IOException("No internet connection")
        }
    }
}