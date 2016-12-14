package com.github.mbarrben.moviedb.di

import android.app.Activity
import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
  fun activity(): Activity
}