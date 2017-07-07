package com.github.mbarrben.moviedb.domain.movies

import com.github.mbarrben.moviedb.domain.movies.MovieMode.Popular
import com.github.mbarrben.moviedb.domain.movies.MovieMode.Search
import com.github.mbarrben.moviedb.domain.navigation.Navigator
import com.github.mbarrben.moviedb.model.entities.Movie
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposables
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class MoviesPresenter
@Inject constructor(val getMovies: GetMovies, val navigator: Navigator) {

  private var moviesSubscription = Disposables.empty()
  private var paginationSubscription = Disposables.empty()
  private var viewSubscriptions = CompositeDisposable()

  private var moviesView: MoviesView? = null

  fun bind(view: MoviesView) {
    moviesView = view

    subscribe(Popular)

    viewSubscriptions += view.movieClicks().subscribe { movie -> navigator.detail(movie) }
    viewSubscriptions += view.searchQueries().subscribe { query -> subscribe(Search(query)) }
    viewSubscriptions += view.searchClosed().subscribe { subscribe(Popular) }
  }

  fun unbind() {
    moviesSubscription.dispose()
    viewSubscriptions.dispose()
    paginationSubscription.dispose()

    moviesView = null
  }

  private fun subscribe(mode: MovieMode) {
    moviesSubscription.dispose()
    moviesSubscription = getMovies.get(mode).subscribeShow(moviesView)

    moviesView?.let { view ->
      paginationSubscription.dispose()
      paginationSubscription = view.paginationEvents()
          .subscribe { page -> addMoviesPage(mode, page) }
    }
  }

  private fun addMoviesPage(mode: MovieMode, page: Int) {
    moviesSubscription.dispose()
    moviesSubscription = getMovies.get(mode, page).subscribeAdd(moviesView)
  }

  private fun Observable<Movie.List>.subscribeShow(view: MoviesView?) = subscribe(
      { view?.show(it) },
      { view?.showError() }
  )

  private fun Observable<Movie.List>.subscribeAdd(view: MoviesView?) = subscribe(
      { view?.addMovies(it) },
      { view?.showError() }
  )

  private fun MoviesView.show(movies: Movie.List) = when {
    movies.isEmpty() -> showEmpty()
    else             -> showMovies(movies)
  }

}
