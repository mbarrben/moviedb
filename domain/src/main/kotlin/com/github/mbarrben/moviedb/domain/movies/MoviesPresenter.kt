package com.github.mbarrben.moviedb.domain.movies

import com.github.mbarrben.moviedb.domain.navigation.Navigator
import com.github.mbarrben.moviedb.model.entities.Movie
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposables
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class MoviesPresenter @Inject constructor(val getMovies: GetMovies, val navigator: Navigator) {

  private var moviesSubscription = Disposables.empty()
  private var viewSubscriptions = CompositeDisposable()

  fun bind(view: MoviesView) {
    checkNotNull(view) { "Set a view before doing anything else in this presenter" }

    moviesSubscription = getMovies.get().subscribe(view)

    viewSubscriptions += view.paginationEvents().subscribe { page ->
      moviesSubscription.dispose()
      moviesSubscription = getMovies.get(page).subscribe(view)
    }

    viewSubscriptions += view.movieClicks().subscribe { navigator.detail(it) }
  }

  fun unbind() {
    moviesSubscription.dispose()
    viewSubscriptions.dispose()
  }

  private fun Observable<Movie.List>.subscribe(view: MoviesView) = subscribe(
      { view.showMovies(it) },
      { view.showError() }
  )

}
