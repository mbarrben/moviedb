package com.github.mbarrben.moviedb.movieDetail

import com.github.mbarrben.moviedb.BaseActivity
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.di.HasComponent
import com.github.mbarrben.moviedb.movies.di.DaggerMovieDetailComponent
import com.github.mbarrben.moviedb.movies.di.MovieDetailComponent

class DetailActivity : BaseActivity(R.layout.detail_activity), HasComponent<MovieDetailComponent> {

  override val component: MovieDetailComponent by lazy {
    DaggerMovieDetailComponent.builder()
        .applicationComponent(applicationComponent)
        .activityModule(activityModule)
        .build()
  }

  override fun inject() = component.inject(this)

}
