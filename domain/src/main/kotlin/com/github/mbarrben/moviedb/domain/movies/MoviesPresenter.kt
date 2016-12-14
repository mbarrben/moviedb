package com.github.mbarrben.moviedb.domain.movies

import com.github.mbarrben.moviedb.model.entities.Movie
import rx.Subscriber
import rx.Subscription
import rx.subscriptions.Subscriptions
import javax.inject.Inject

class MoviesPresenter @Inject constructor(val getMovies: GetMovies) {

  private var subscription: Subscription = Subscriptions.empty()

  fun bind(view: MoviesView) {
    checkNotNull(view) { "Set a view before doing anything else in this presenter" }

    subscription = getMovies.get()
        .subscribe(MoviesSubscriber(view))
  }

  fun unbind() {
    subscription.unsubscribe()
  }

  private class MoviesSubscriber(val view: MoviesView) : Subscriber<Movie.List>() {
    override fun onCompleted() = Unit
    override fun onError(e: Throwable) = view.showError()
    override fun onNext(movies: Movie.List) = view.showMovies(movies)
  }
}
