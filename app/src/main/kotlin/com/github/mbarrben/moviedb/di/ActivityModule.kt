package com.github.mbarrben.moviedb.di

import android.app.Activity
import com.github.mbarrben.moviedb.domain.navigation.Navigator
import com.github.mbarrben.moviedb.navigation.NavigatorImpl
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val activity: Activity) {
  @Provides @PerActivity fun activity(): Activity = activity
  @Provides fun navigator(navigatorImpl: NavigatorImpl): Navigator = navigatorImpl
}