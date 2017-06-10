package com.github.mbarrben.moviedb.movies.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup
import android.support.v7.widget.SearchView
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.KeyEvent.ACTION_UP
import android.view.KeyEvent.KEYCODE_BACK
import android.view.MenuItem
import android.view.View
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
import com.jakewharton.rxbinding2.support.v4.view.actionViewEvents
import com.jakewharton.rxbinding2.support.v7.widget.queryTextChanges
import com.jakewharton.rxbinding2.view.MenuItemActionViewCollapseEvent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit.MILLISECONDS
import kotlinx.android.synthetic.main.movies_view.view.movies_empty_view as emptyView
import kotlinx.android.synthetic.main.movies_view.view.movies_recycler as recycler
import kotlinx.android.synthetic.main.movies_view.view.movies_toolbar as toolbar

class MoviesViewLayout
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
  : ConstraintLayout(context, attrs, defStyleAttr),
    MoviesView {

  companion object {
    private val VISIBLE_THRESHOLD = 4
  }

  private val moviesAdapter = MoviesAdapter()
  private val searchView: SearchView
  private val searchItem: MenuItem

  init {
    inflate(R.layout.movies_view, attachToRoot = true)

    searchItem = initSearchItem()
    searchView = initSearchView(searchItem)
    initRecycler()
  }

  override fun dispatchKeyEventPreIme(event: KeyEvent) = when {
    super.dispatchKeyEventPreIme(event) -> true
    event.isKeyPressed(KEYCODE_BACK)    -> searchItem.collapseActionView()
    else                                -> false
  }

  override fun showMovies(movies: Movie.List) {
    moviesAdapter.movies = movies
    hideLoading()
    emptyView.visibility = GONE
  }

  override fun addMovies(movies: Movie.List) {
    moviesAdapter.movies += movies
    hideLoading()
  }

  override fun showLoading() {
    moviesAdapter.loading = true
  }

  override fun hideLoading() {
    moviesAdapter.loading = false
  }

  override fun showError() {
    hideLoading()
    Snackbar.make(this, R.string.error_loading_movies, Snackbar.LENGTH_SHORT)
        .show()
  }

  override fun showEmpty() {
    showMovies(Movie.List.EMPTY)
    emptyView.visibility = VISIBLE
  }

  override fun paginationEvents(): Observable<Int> = recycler.scrollToEndEvents(VISIBLE_THRESHOLD)
      .filter { !moviesAdapter.loading }
      .map { moviesAdapter.movies.page + 1 }
      .doOnEach { showLoading() }
      .doOnEach { Timber.d { "infinite scroll" } }

  override fun movieClicks() = moviesAdapter.itemClicks

  override fun searchQueries(): Observable<String> = searchView.queryTextChanges()
      .debounce(400, MILLISECONDS)
      .map { it.trim() }
      .filter { it.isNotEmpty() }
      .distinct()
      .map { it.toString() }
      .doOnEach { Timber.d { "query $it" } }
      .observeOn(AndroidSchedulers.mainThread())

  override fun searchClosed(): Observable<Unit> = searchItem.actionViewEvents()
      .filter { it is MenuItemActionViewCollapseEvent }
      .map { Unit }

  fun findMoviePosterView(movie: Movie): View? = recycler.findViewHolderForItemId(movie.id).itemView

  private fun initSearchItem(): MenuItem {
    toolbar.inflateMenu(R.menu.movies_menu)
    return toolbar.menu.findItem(R.id.search)
  }

  private fun initSearchView(searchItem: MenuItem) = searchItem.actionView as SearchView

  private fun initRecycler() {
    val spanCount = context.resources.getInteger(R.integer.card_span_count)

    val gridLayoutManager = GridLayoutManager(context, spanCount).apply {
      spanSizeLookup = object : SpanSizeLookup() {
        override fun getSpanSize(position: Int) = when (Type.from(moviesAdapter.getItemViewType(position))) {
          MOVIE   -> 1
          LOADING -> spanCount
        }
      }
    }

    with(recycler) {
      layoutManager = gridLayoutManager
      setHasFixedSize(true)
      addItemDecoration(MovieItemDecoration(context, spanCount))
      adapter = moviesAdapter
    }
  }

  private fun KeyEvent.isKeyPressed(keyCode: Int) = this.keyCode == keyCode && action == ACTION_UP
}
