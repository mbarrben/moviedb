package com.github.mbarrben.moviedb.app

import android.app.Application

class MovieDbApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initStrictMode()
        initLogger()
        DependencyInjection.init(this)
    }
}