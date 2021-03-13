package com.github.mbarrben.moviedb.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieDbApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initStrictMode()
        initLogger()
    }
}