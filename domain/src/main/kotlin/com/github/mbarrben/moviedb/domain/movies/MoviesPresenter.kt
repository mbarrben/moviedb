package com.github.mbarrben.moviedb.domain.movies

import com.github.mbarrben.moviedb.domain.navigation.Navigator
import com.github.mbarrben.moviedb.model.entities.Movie
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposables
import io.reactivex.rxkotlin.plusAssign
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject

class MoviesPresenter
@Inject constructor(val getMovies: GetMovies, val navigator: Navigator) {

  private var moviesSubscription = Disposables.empty()
  private var viewSubscriptions = CompositeDisposable()

  private var moviesView: MoviesView? = null
  private var searchView: MovieSearchView? = null

  fun bind(view: MoviesView) {
    checkNotNull(view) { "Set a view before doing anything else in this presenter" }

    moviesView = view

    moviesSubscription = getMovies.get().subscribe(view)

    viewSubscriptions += view.paginationEvents().subscribe { page ->
      moviesView?.let {
        moviesSubscription.dispose()
        moviesSubscription = getMovies.get(page).subscribe(it)
      }
    }

    viewSubscriptions += view.movieClicks().subscribe { navigator.detail(it) }

    viewSubscriptions += view.searchQueries()
        .debounce(200, MILLISECONDS)
        .filter { it.isNotEmpty() }
        .subscribe { println("$it query submitted") }

    viewSubscriptions += view.searchClosed()
        .subscribe {
          moviesView?.let {
            println("search closed")
            moviesSubscription.dispose()
            moviesSubscription = getMovies.get().subscribe(it)
          }
        }
  }

  fun unbind() {
    moviesSubscription.dispose()
    viewSubscriptions.dispose()

    moviesView = null
    searchView = null
  }

  private fun Observable<Movie.List>.subscribe(view: MoviesView) = subscribe(
      { view.showMovies(it) },
      { view.showError() }
  )

}
