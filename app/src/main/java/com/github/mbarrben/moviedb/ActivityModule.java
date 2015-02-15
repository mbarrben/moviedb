package com.github.mbarrben.moviedb;

import android.app.Activity;
import android.content.Context;
import com.github.mbarrben.moviedb.di.ActivityContext;
import dagger.Module;
import dagger.Provides;

@Module(library = true)
public class ActivityModule {

  private final Activity activity;

  public ActivityModule(Activity activity) {
    this.activity = activity;
  }

  @ActivityContext @Provides Context provideActivityContext() {
    return activity;
  }
}
