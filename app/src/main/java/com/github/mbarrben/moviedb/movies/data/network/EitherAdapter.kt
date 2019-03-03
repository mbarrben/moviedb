package com.github.mbarrben.moviedb.movies.data.network

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import retrofit2.Call
import java.io.IOException

fun <L : Any, R : Any> Call<R>.toEither(
    notFoundError: L,
    connectionError: L
): Either<L, R> = try {
    val response = execute()
    when (response.code()) {
        in 200..204 -> response.body()?.right() ?: notFoundError.left()
        else -> notFoundError.left()
    }
} catch (e: IOException) {
    connectionError.left()
}