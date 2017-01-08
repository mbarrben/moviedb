package com.github.mbarrben.moviedb.movies.di

import com.github.mbarrben.moviedb.di.ActivityComponent
import com.github.mbarrben.moviedb.di.ActivityModule
import com.github.mbarrben.moviedb.di.ApplicationComponent
import com.github.mbarrben.moviedb.di.PerActivity
import com.github.mbarrben.moviedb.movieDetail.view.DetailLayout
import com.github.mbarrben.moviedb.movieDetail.view.platform.DetailActivity
import com.github.mbarrben.moviedb.movieDetail.view.platform.DetailFragment
import dagger.Component

@PerActivity
@Component(
    dependencies = arrayOf(ApplicationComponent::class),
    modules = arrayOf(
        ActivityModule::class,
        MovieDetailModule::class
    )
)
interface MovieDetailComponent : ActivityComponent {
  fun inject(activity: DetailActivity)
  fun inject(fragment: DetailFragment)
  fun inject(view: DetailLayout)
}