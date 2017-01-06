package com.github.mbarrben.moviedb.movies.view

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.domain.movies.MoviesView
import com.github.mbarrben.moviedb.extensions.Timber
import com.github.mbarrben.moviedb.extensions.inflate
import com.github.mbarrben.moviedb.extensions.scrollToEndEvents
import com.github.mbarrben.moviedb.model.entities.Movie
import com.github.mbarrben.moviedb.movies.view.adapter.MoviesAdapter
import com.github.mbarrben.moviedb.movies.view.adapter.Type
import com.github.mbarrben.moviedb.movies.view.adapter.Type.LOADING
import com.github.mbarrben.moviedb.movies.view.adapter.Type.MOVIE
import kotlinx.android.synthetic.main.movies_view.view.moviesRecycler
import rx.Observable

class MoviesViewLayout(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs), MoviesView {

  companion object {
    private val VISIBLE_THRESHOLD = 4
  }

  private val moviesAdapter = MoviesAdapter()

  init {
    inflate(R.layout.movies_view, attachToRoot = true)

    val gridLayoutManager = GridLayoutManager(context, context.resources.getInteger(R.integer.card_span_count)).apply {
      spanSizeLookup = object : SpanSizeLookup() {
        override fun getSpanSize(position: Int) = when (Type.from(moviesAdapter.getItemViewType(position))) {
          MOVIE   -> 1
          LOADING -> spanCount
        }
      }
    }

    with(moviesRecycler) {
      layoutManager = gridLayoutManager
      setHasFixedSize(true)
      addItemDecoration(MovieItemDecoration(context))
      adapter = moviesAdapter
    }
  }

  override fun showMovies(movies: Movie.List) {
    moviesAdapter.movies += movies
    hideLoading()
  }

  override fun showLoading() {
    moviesAdapter.loading = true
  }

  override fun hideLoading() {
    moviesAdapter.loading = false
  }

  override fun showError() = hideLoading()

  override fun paginationEvents(): Observable<Int> = moviesRecycler.scrollToEndEvents(VISIBLE_THRESHOLD)
      .filter { !moviesAdapter.loading }
      .map { moviesAdapter.movies.page + 1 }
      .doOnEach { showLoading() }
      .doOnEach { Timber.d { "infinite scroll" } }

  override fun movieClicks() = moviesAdapter.itemClicks

  fun findMoviePosterView(movie: Movie): View? {
    val pos = moviesAdapter.findPositionById(movie.id)
    return moviesRecycler.layoutManager.findViewByPosition(pos)
  }
}
