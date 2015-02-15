package com.github.mbarrben.moviedb;

import android.content.Context;
import android.view.LayoutInflater;
import dagger.Module;
import dagger.Provides;

@Module(
    injects = {
        MovieDbApp.class
    },
    library = true
)
public class RootModule {

  private final Context context;

  public RootModule(Context context) {
    this.context = context;
  }

  @Provides Context provideApplicationContext() {
    return context;
  }

  @Provides LayoutInflater provideLayoutInflater() {
    return LayoutInflater.from(context);
  }
}
