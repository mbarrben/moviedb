package com.github.mbarrben.moviedb.model.rest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY

object NetworkClient {

  fun create(): OkHttpClient = OkHttpClient.Builder()
      .addInterceptor(HttpLoggingInterceptor().setLevel(BODY))
      .build()

}
