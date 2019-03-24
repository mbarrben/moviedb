package com.github.mbarrben.moviedb.commons.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CallMother {
    companion object {
        fun <T> aCall(action: () -> Response<T>) = object : Call<T> {
            override fun enqueue(callback: Callback<T>) = TODO("not implemented")
            override fun isExecuted() = TODO("not implemented")
            override fun clone() = TODO("not implemented")
            override fun isCanceled() = TODO("not implemented")
            override fun cancel() = TODO("not implemented")
            override fun execute() = action()
            override fun request() = TODO("not implemented")
        }
    }
}

