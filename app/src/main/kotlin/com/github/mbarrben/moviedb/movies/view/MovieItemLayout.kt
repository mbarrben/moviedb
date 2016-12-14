package com.github.mbarrben.moviedb.movies.view

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.domain.movies.MovieView
import com.github.mbarrben.moviedb.extensions.inflate
import com.github.mbarrben.moviedb.extensions.load
import com.github.mbarrben.moviedb.model.entities.Movie
import kotlinx.android.synthetic.main.movies_item_view.view.picture

class MovieItemLayout(context: Context, attrs: AttributeSet) : CardView(context, attrs), MovieView {

  init {
    inflate(R.layout.movies_item_view, true)
  }

  override fun render(movie: Movie) {
    picture.contentDescription = movie.title
    picture.load(movie.posterPath()) { fit() }
  }
}
