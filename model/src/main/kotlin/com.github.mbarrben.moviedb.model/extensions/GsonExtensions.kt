package com.github.mbarrben.moviedb.model.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T : Any> Gson.fromJson(json: String): T = fromJson<T>(json, TypeToken.get(T::class.java).type)
