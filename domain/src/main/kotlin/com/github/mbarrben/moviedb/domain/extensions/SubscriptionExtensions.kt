package com.github.mbarrben.moviedb.domain.extensions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * disposable -= observable.subscribe{}
 */
operator fun CompositeDisposable.minusAssign(disposable: Disposable): Unit {
  remove(disposable)
}