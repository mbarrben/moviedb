package com.github.mbarrben.moviedb.movieDetail.view

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.domain.moviesDetail.DetailView
import com.github.mbarrben.moviedb.extensions.inflate
import com.github.mbarrben.moviedb.model.entities.Movie
import com.jakewharton.rxbinding.view.clicks
import kotlinx.android.synthetic.main.detail_view.view.collapsingToolbar
import kotlinx.android.synthetic.main.detail_view.view.fab
import kotlinx.android.synthetic.main.detail_view.view.toolbar

class DetailLayout(context: Context, attrs: AttributeSet) : CoordinatorLayout(context, attrs), DetailView {

  init {
    inflate(R.layout.detail_view, attachToRoot = true)

    with(context as AppCompatActivity) {
      setSupportActionBar(toolbar)
      supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
  }

  override fun render(movie: Movie) {
    collapsingToolbar.title = movie.title

    fab.clicks().subscribe {
      Snackbar.make(fab, "Replace with your own action ${movie.title}", Snackbar.LENGTH_LONG)
          .setAction("Action", null)
          .show()
    }
  }
}