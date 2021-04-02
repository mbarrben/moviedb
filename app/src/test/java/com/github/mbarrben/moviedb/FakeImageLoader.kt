package com.github.mbarrben.moviedb

import coil.ImageLoader
import coil.bitmap.BitmapPool
import coil.memory.MemoryCache
import coil.request.DefaultRequestOptions
import coil.request.Disposable
import coil.request.ImageRequest
import coil.request.ImageResult
import com.nhaarman.mockitokotlin2.mock

object FakeImageLoader : ImageLoader {
    override val bitmapPool: BitmapPool = mock()
    override val defaults: DefaultRequestOptions = mock()
    override val memoryCache: MemoryCache = mock()
    override fun enqueue(request: ImageRequest): Disposable = mock()
    override suspend fun execute(request: ImageRequest): ImageResult = mock()
    override fun shutdown() = Unit
}