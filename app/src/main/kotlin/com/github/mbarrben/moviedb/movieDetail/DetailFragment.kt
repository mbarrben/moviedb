package com.github.mbarrben.moviedb.movieDetail

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.github.mbarrben.moviedb.BaseFragment
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.extensions.fromJson
import com.github.mbarrben.moviedb.model.entities.Movie
import com.github.mbarrben.moviedb.movies.di.MovieDetailComponent
import com.google.gson.Gson
import com.jakewharton.rxbinding.view.clicks
import kotlinx.android.synthetic.main.detail_fragment.fab
import kotlinx.android.synthetic.main.detail_fragment.toolbar
import kotlinx.android.synthetic.main.detail_fragment.toolbar_layout
import javax.inject.Inject

class DetailFragment : BaseFragment(R.layout.detail_fragment) {

  @Inject lateinit var gson: Gson
  @Inject lateinit var activity: AppCompatActivity

  override fun inject() = getComponent(MovieDetailComponent::class.java).inject(this)

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    activity.setSupportActionBar(toolbar)
    activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

    val movie = gson.fromJson<Movie>(activity.intent.getStringExtra("movie"))

    toolbar_layout.title = "toolbar layout"

    fab.clicks().subscribe {
      Snackbar.make(fab, "Replace with your own action ${movie.title}", Snackbar.LENGTH_LONG)
          .setAction("Action", null)
          .show()
    }
  }
}
