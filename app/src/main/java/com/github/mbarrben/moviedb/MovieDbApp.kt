package com.github.mbarrben.moviedb

import android.app.Application
import timber.log.Timber

class MovieDbApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}