package com.github.mbarrben.moviedb.di

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
  fun activity(): Activity
  fun appCompatActivity(): AppCompatActivity
}