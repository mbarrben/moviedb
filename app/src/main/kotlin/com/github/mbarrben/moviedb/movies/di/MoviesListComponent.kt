package com.github.mbarrben.moviedb.movies.di

import com.github.mbarrben.moviedb.di.ActivityComponent
import com.github.mbarrben.moviedb.di.ActivityModule
import com.github.mbarrben.moviedb.di.ApplicationComponent
import com.github.mbarrben.moviedb.di.PerActivity
import com.github.mbarrben.moviedb.movies.view.MovieItemLayout
import com.github.mbarrben.moviedb.movies.view.MoviesActivity
import com.github.mbarrben.moviedb.movies.view.MoviesFragment
import dagger.Component

@PerActivity
@Component(
    dependencies = arrayOf(ApplicationComponent::class),
    modules = arrayOf(
        ActivityModule::class,
        MoviesListModule::class
    )
)
interface MoviesListComponent : ActivityComponent {
  fun inject(moviesActivity: MoviesActivity)
  fun inject(moviesFragment: MoviesFragment)
  fun inject(movieItemLayout: MovieItemLayout)
}