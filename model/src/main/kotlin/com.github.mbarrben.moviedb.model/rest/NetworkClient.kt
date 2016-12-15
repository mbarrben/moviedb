package com.github.mbarrben.moviedb.model.rest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Logger

object NetworkClient {

  fun create(log: (String) -> Unit = {}): OkHttpClient = OkHttpClient.Builder()
      .addInterceptor(HttpLoggingInterceptor(Logger(log)).setLevel(BODY))
      .build()

}
