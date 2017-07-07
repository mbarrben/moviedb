package com.github.mbarrben.moviedb.extensions

import io.reactivex.Observable
import io.reactivex.Observable.empty
import io.reactivex.Observable.just

fun <T : Any> Observable<T?>.filterNotNull(): Observable<T> = flatMap { if (it == null) empty() else just(it) }