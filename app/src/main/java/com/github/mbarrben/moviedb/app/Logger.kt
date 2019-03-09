package com.github.mbarrben.moviedb.app

import com.github.mbarrben.moviedb.BuildConfig
import timber.log.Timber

internal fun initLogger() {
    if (BuildConfig.DEBUG) {
        Timber.plant(Timber.DebugTree())
    }
}
