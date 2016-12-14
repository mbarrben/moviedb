package com.github.mbarrben.moviedb

import android.app.Application
import com.github.mbarrben.moviedb.di.ApplicationComponent
import com.github.mbarrben.moviedb.di.ApplicationModule
import com.github.mbarrben.moviedb.di.DaggerApplicationComponent

class MovieDbApp : Application() {

  val applicationComponent: ApplicationComponent = DaggerApplicationComponent.builder()
      .applicationModule(ApplicationModule(this))
      .build()

  override fun onCreate() {
    super.onCreate()
    applicationComponent.inject(this)
  }

}
