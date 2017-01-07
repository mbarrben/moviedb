package com.github.mbarrben.moviedb.movieDetail.view

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.format.DateFormat.getMediumDateFormat
import android.util.AttributeSet
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.domain.moviesDetail.DetailView
import com.github.mbarrben.moviedb.extensions.Timber.d
import com.github.mbarrben.moviedb.extensions.getComponent
import com.github.mbarrben.moviedb.extensions.inflate
import com.github.mbarrben.moviedb.extensions.load
import com.github.mbarrben.moviedb.extensions.transitionName
import com.github.mbarrben.moviedb.model.entities.Movie
import com.github.mbarrben.moviedb.movies.di.MovieDetailComponent
import com.jakewharton.rxbinding.view.clicks
import com.squareup.picasso.Picasso
import javax.inject.Inject
import kotlinx.android.synthetic.main.detail_content.view.detail_backdrop as backdrop
import kotlinx.android.synthetic.main.detail_content.view.detail_overview as overview
import kotlinx.android.synthetic.main.detail_content.view.detail_rating_bar as ratingBar
import kotlinx.android.synthetic.main.detail_content.view.detail_release_date as releaseDate
import kotlinx.android.synthetic.main.detail_content.view.detail_votes as votes
import kotlinx.android.synthetic.main.detail_view.view.detail_collapsing_toolbar as collapsingToolbar
import kotlinx.android.synthetic.main.detail_view.view.detail_fab as fab
import kotlinx.android.synthetic.main.detail_view.view.detail_poster as poster
import kotlinx.android.synthetic.main.detail_view.view.detail_toolbar as toolbar

class DetailLayout(context: Context, attrs: AttributeSet) : CoordinatorLayout(context, attrs), DetailView {

  @Inject lateinit var picasso: Picasso

  val activity: AppCompatActivity = (context as AppCompatActivity).apply {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportPostponeEnterTransition()
  }

  init {
    inflate(R.layout.detail_view, attachToRoot = true)
    inject()
  }

  override fun render(movie: Movie) {
    collapsingToolbar.title = movie.title
    overview.text = movie.overview
    releaseDate.text = getMediumDateFormat(context).format(movie.releaseDate)
    ratingBar.rating = movie.voteAverage / 2
    votes.text = context.resources.getQuantityString(R.plurals.vote_count, movie.voteCount, movie.voteCount)

    poster.contentDescription = movie.title
    poster.transitionName("transition")
    poster.load(picasso, movie.posterPath()) { activity.supportStartPostponedEnterTransition() }

    backdrop.load(picasso, movie.backdropPath())

    fab.clicks().subscribe {
      Snackbar.make(fab, "Replace with your own action ${movie.title}", Snackbar.LENGTH_LONG)
          .setAction("Action", null)
          .show()
    }
  }

  override fun details(details: Movie.Details) = d { "Movie details loaded: $details" }

  private fun inject() = getComponent(MovieDetailComponent::class.java).inject(this)
}