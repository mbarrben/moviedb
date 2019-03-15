package com.github.mbarrben.moviedb.movies.data.network

import com.github.mbarrben.moviedb.movies.data.network.model.Dto
import com.nhaarman.mockitokotlin2.mock
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> aCall(action: () ->   Response<T>) = object : Call<T> {
    override fun enqueue(callback: Callback<T>) = TODO("not implemented")
    override fun isExecuted() = TODO("not implemented")
    override fun clone() = TODO("not implemented")
    override fun isCanceled() = TODO("not implemented")
    override fun cancel() = TODO("not implemented")
    override fun execute() = action()
    override fun request() = TODO("not implemented")
}