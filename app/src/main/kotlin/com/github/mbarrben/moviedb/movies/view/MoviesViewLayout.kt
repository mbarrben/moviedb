package com.github.mbarrben.moviedb.movies.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.LayoutManager
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.domain.movies.MoviesView
import com.github.mbarrben.moviedb.extensions.Timber
import com.github.mbarrben.moviedb.extensions.inflate
import com.github.mbarrben.moviedb.model.entities.Movie
import com.jakewharton.rxbinding.support.v7.widget.RecyclerViewScrollEvent
import com.jakewharton.rxbinding.support.v7.widget.scrollEvents
import kotlinx.android.synthetic.main.movies_view.view.moviesRecycler
import rx.Observable

class MoviesViewLayout(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs), MoviesView {

  companion object {
    private val VISIBLE_THRESHOLD = 4
  }

  private val moviesAdapter = MoviesAdapter()

  private var isLoading = false

  init {
    inflate(R.layout.movies_view, attachToRoot = true)
    with(moviesRecycler) {
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
    isLoading = true
  }

  override fun hideLoading() {
    isLoading = false
  }

  override fun showError() = hideLoading()

  override fun paginationEvents(): Observable<Int> = moviesRecycler.scrollEvents()
      .filter { !isLoading }
      .filter { it.isScrollingUp() }
      .filter { moviesRecycler.lastXItemsAreVisible(VISIBLE_THRESHOLD) }
      .map { moviesAdapter.movies.page + 1 }
      .doOnEach { showLoading() }
      .doOnEach { Timber.d { "infinite scroll" } }

  private fun RecyclerViewScrollEvent.isScrollingUp() = dy() > 0

  private fun RecyclerView.lastXItemsAreVisible(x: Int) = with(layoutManager) {
    itemCount <= lastVisibleItemPosition() + x
  }

  private fun LayoutManager.lastVisibleItemPosition() = when (this) {
    is LinearLayoutManager -> findLastCompletelyVisibleItemPosition()
    else                   -> 0
  }

}
