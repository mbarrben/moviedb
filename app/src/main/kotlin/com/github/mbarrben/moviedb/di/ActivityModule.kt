package com.github.mbarrben.moviedb.di

import android.app.Activity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val activity: Activity) {
  @Provides @PerActivity fun activity(): Activity = activity
}