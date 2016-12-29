package com.github.mbarrben.moviedb.movieDetail

import android.os.Bundle
import android.support.design.widget.Snackbar
import com.github.mbarrben.moviedb.BaseActivity
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.di.HasComponent
import com.github.mbarrben.moviedb.extensions.fromJson
import com.github.mbarrben.moviedb.model.entities.Movie
import com.github.mbarrben.moviedb.movies.di.DaggerMovieDetailComponent
import com.github.mbarrben.moviedb.movies.di.MovieDetailComponent
import com.google.gson.Gson
import com.jakewharton.rxbinding.view.clicks
import kotlinx.android.synthetic.main.detail_activity.fab
import kotlinx.android.synthetic.main.detail_activity.toolbar
import javax.inject.Inject

class DetailActivity : BaseActivity(R.layout.detail_activity), HasComponent<MovieDetailComponent> {

  @Inject lateinit var gson: Gson

  override val component: MovieDetailComponent by lazy {
    DaggerMovieDetailComponent.builder()
        .applicationComponent(applicationComponent)
        .activityModule(activityModule)
        .build()
  }

  override fun inject() = component.inject(this)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val movie = gson.fromJson<Movie>(intent.getStringExtra("movie"))

    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    fab.clicks().subscribe {
      Snackbar.make(fab, "Replace with your own action ${movie.title}", Snackbar.LENGTH_LONG)
          .setAction("Action", null)
          .show()
    }
  }
}
