package com.github.mbarrben.moviedb.movies.view.platform

import com.github.mbarrben.moviedb.BaseActivity
import com.github.mbarrben.moviedb.R.layout
import com.github.mbarrben.moviedb.di.HasComponent
import com.github.mbarrben.moviedb.movies.di.DaggerMoviesListComponent
import com.github.mbarrben.moviedb.movies.di.MoviesListComponent
import com.github.mbarrben.moviedb.movies.di.MoviesListModule

class MoviesActivity : BaseActivity(layout.movies_activity), HasComponent<MoviesListComponent> {

  override val component: MoviesListComponent by lazy {
    DaggerMoviesListComponent.builder()
        .applicationComponent(applicationComponent)
        .activityModule(activityModule)
        .moviesListModule(MoviesListModule())
        .build()
  }

  override fun inject() = component.inject(this)

}
