package com.github.mbarrben.moviedb.domain.movies

import com.github.mbarrben.moviedb.model.entities.Movie
import rx.Subscriber
import rx.lang.kotlin.plusAssign
import rx.subscriptions.CompositeSubscription
import rx.subscriptions.Subscriptions
import javax.inject.Inject

class MoviesPresenter @Inject constructor(val getMovies: GetMovies) {

  private var moviesSubscription = Subscriptions.empty()
  private var viewSubscriptions = CompositeSubscription()

  fun bind(view: MoviesView) {
    checkNotNull(view) { "Set a view before doing anything else in this presenter" }

    moviesSubscription = getMovies.get().subscribe(MoviesSubscriber(view))

    viewSubscriptions += view.paginationEvents().subscribe { page ->
      moviesSubscription.unsubscribe()
      moviesSubscription = getMovies.get(page).subscribe(MoviesSubscriber(view))
    }

    viewSubscriptions += view.movieClicks()
        .subscribe { println("presenter item click") }
  }

  fun unbind() {
    moviesSubscription.unsubscribe()
    viewSubscriptions.unsubscribe()
  }

  private class MoviesSubscriber(val view: MoviesView) : Subscriber<Movie.List>() {
    override fun onCompleted() = Unit
    override fun onError(e: Throwable) = view.showError()
    override fun onNext(movies: Movie.List) = view.showMovies(movies)
  }
}
