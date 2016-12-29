package com.github.mbarrben.moviedb.movieDetail.view

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.format.DateFormat.getMediumDateFormat
import android.util.AttributeSet
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.domain.moviesDetail.DetailView
import com.github.mbarrben.moviedb.extensions.getComponent
import com.github.mbarrben.moviedb.extensions.inflate
import com.github.mbarrben.moviedb.extensions.load
import com.github.mbarrben.moviedb.model.entities.Movie
import com.github.mbarrben.moviedb.movies.di.MovieDetailComponent
import com.jakewharton.rxbinding.view.clicks
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_content.view.overview
import kotlinx.android.synthetic.main.detail_content.view.ratingBar
import kotlinx.android.synthetic.main.detail_content.view.releaseDate
import kotlinx.android.synthetic.main.detail_content.view.votes
import kotlinx.android.synthetic.main.detail_view.view.collapsingToolbar
import kotlinx.android.synthetic.main.detail_view.view.fab
import kotlinx.android.synthetic.main.detail_view.view.poster
import kotlinx.android.synthetic.main.detail_view.view.toolbar
import javax.inject.Inject

class DetailLayout(context: Context, attrs: AttributeSet) : CoordinatorLayout(context, attrs), DetailView {

  @Inject lateinit var picasso: Picasso

  init {
    inflate(R.layout.detail_view, attachToRoot = true)

    inject()

    with(context as AppCompatActivity) {
      setSupportActionBar(toolbar)
      supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
  }

  override fun render(movie: Movie) {
    collapsingToolbar.title = movie.title
    overview.text = movie.overview
    releaseDate.text = getMediumDateFormat(context).format(movie.releaseDate)
    ratingBar.rating = movie.voteAverage / 2
    votes.text = context.resources.getQuantityString(R.plurals.vote_count, movie.voteCount, movie.voteCount)
    poster.load(picasso, movie.posterPath())

    fab.clicks().subscribe {
      Snackbar.make(fab, "Replace with your own action ${movie.title}", Snackbar.LENGTH_LONG)
          .setAction("Action", null)
          .show()
    }
  }

  private fun inject() = getComponent(MovieDetailComponent::class.java).inject(this)
}