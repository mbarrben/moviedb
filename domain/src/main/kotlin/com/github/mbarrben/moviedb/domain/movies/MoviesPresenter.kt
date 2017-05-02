package com.github.mbarrben.moviedb.domain.movies

import com.github.mbarrben.moviedb.domain.navigation.Navigator
import com.github.mbarrben.moviedb.model.entities.Movie
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposables
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class MoviesPresenter
@Inject constructor(val getMovies: GetMovies, val searchMovies: SearchMovies, val navigator: Navigator) {

  private var moviesSubscription = Disposables.empty()
  private var paginationSubscription = Disposables.empty()
  private var viewSubscriptions = CompositeDisposable()

  private var moviesView: MoviesView? = null

  fun bind(view: MoviesView) {
    checkNotNull(view) { "Set a view before doing anything else in this presenter" }

    moviesView = view

    subscribeGetPagination()
    subscribeGet()

    viewSubscriptions += view.movieClicks().subscribe { navigator.detail(it) }

    viewSubscriptions += view.searchQueries()
        .doOnNext { subscribeSearchPagination(it) }
        .subscribe { subscribeSearch(it) }

    viewSubscriptions += view.searchClosed()
        .doOnNext { subscribeGetPagination() }
        .subscribe { subscribeGet() }
  }

  fun unbind() {
    moviesSubscription.dispose()
    viewSubscriptions.dispose()
    paginationSubscription.dispose()

    moviesView = null
  }

  private fun subscribeGet() {
    moviesSubscription.dispose()
    moviesSubscription = getMovies.get()
        .subscribeShow(moviesView)
  }

  private fun subscribeGetPagination() = moviesView?.let { view ->
    paginationSubscription.dispose()
    paginationSubscription = view.paginationEvents().subscribe { page -> addGetMoviesPage(page) }
  }

  private fun addGetMoviesPage(page: Int) {
    moviesSubscription.dispose()
    moviesSubscription = getMovies.get(page).subscribeAdd(moviesView)
  }

  private fun subscribeSearch(query: String) {
    moviesSubscription.dispose()
    moviesSubscription = searchMovies.search(query).subscribeShow(moviesView)
  }

  private fun subscribeSearchPagination(query: String) = moviesView?.let { view ->
    paginationSubscription.dispose()
    paginationSubscription = view.paginationEvents().subscribe { page -> addSearchMoviesPage(query, page) }
  }

  private fun addSearchMoviesPage(query: String, page: Int) {
    moviesSubscription.dispose()
    moviesSubscription = searchMovies.search(query, page).subscribeAdd(moviesView)
  }

  private fun Observable<Movie.List>.subscribeShow(view: MoviesView?) = view?.let {
    subscribe(
        { view.showMovies(it) },
        { view.showError() }
    )
  }

  private fun Observable<Movie.List>.subscribeAdd(view: MoviesView?) = view?.let {
    subscribe(
        { view.addMovies(it) },
        { view.showError() }
    )
  }

}
