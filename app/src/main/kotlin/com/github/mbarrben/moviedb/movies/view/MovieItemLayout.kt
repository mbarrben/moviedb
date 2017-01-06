package com.github.mbarrben.moviedb.movies.view

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.domain.movies.MovieView
import com.github.mbarrben.moviedb.extensions.getComponent
import com.github.mbarrben.moviedb.extensions.inflate
import com.github.mbarrben.moviedb.extensions.load
import com.github.mbarrben.moviedb.model.entities.Movie
import com.github.mbarrben.moviedb.movies.di.MoviesListComponent
import com.jakewharton.rxbinding.view.clicks
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movies_item_view.view.picture
import rx.Observable
import javax.inject.Inject

class MovieItemLayout @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) : CardView(context, attrs, defStyleAttr),
                             MovieView {

  @Inject lateinit var picasso: Picasso

  private var movie: Movie? = null

  init {
    inflate(R.layout.movies_item_view, attachToRoot = true)
    inject()
  }

  override fun render(movie: Movie) {
    this.movie = movie
    picture.contentDescription = movie.title
    picture.load(picasso, movie.posterPath())
  }

  override fun movieClicks(): Observable<Movie> = clicks().filter { movie != null }
      .map { movie }

  private fun inject() = getComponent(MoviesListComponent::class.java).inject(this)
}
