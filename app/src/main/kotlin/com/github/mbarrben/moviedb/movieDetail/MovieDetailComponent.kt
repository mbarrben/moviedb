package com.github.mbarrben.moviedb.movies.di

import com.github.mbarrben.moviedb.di.ActivityComponent
import com.github.mbarrben.moviedb.di.ActivityModule
import com.github.mbarrben.moviedb.di.ApplicationComponent
import com.github.mbarrben.moviedb.di.PerActivity
import com.github.mbarrben.moviedb.movieDetail.DetailActivity
import dagger.Component

@PerActivity
@Component(
    dependencies = arrayOf(ApplicationComponent::class),
    modules = arrayOf(
        ActivityModule::class
    )
)
interface MovieDetailComponent : ActivityComponent {
  fun inject(detailActivity: DetailActivity)
}