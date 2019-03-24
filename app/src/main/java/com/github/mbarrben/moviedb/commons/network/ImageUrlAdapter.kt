package com.github.mbarrben.moviedb.commons.network

import com.github.mbarrben.moviedb.movies.data.network.model.ImageUrl
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson


class ImageUrlAdapter(
    private val baseUrl: String
) {
    @ToJson
    fun toJson(imageUrl: ImageUrl): String = imageUrl.url.substring(baseUrl.length)

    @FromJson
    fun fromJson(path: String): ImageUrl = ImageUrl(baseUrl + path)
}