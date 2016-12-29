package com.github.mbarrben.moviedb.di

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import com.github.mbarrben.moviedb.domain.navigation.Navigator
import com.github.mbarrben.moviedb.navigation.NavigatorImpl
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val activity: AppCompatActivity) {
  @Provides @PerActivity fun appCompatActivity(): AppCompatActivity = activity
  @Provides @PerActivity fun activity(): Activity = activity
  @Provides fun navigator(navigatorImpl: NavigatorImpl): Navigator = navigatorImpl
}