package com.github.mbarrben.moviedb.domain.movies

import com.github.mbarrben.moviedb.model.entities.Movie
import rx.Subscriber
import rx.subscriptions.Subscriptions
import javax.inject.Inject

class MoviesPresenter @Inject constructor(val getMovies: GetMovies) {

  private var movieSubscription = Subscriptions.empty()
  private var infiniteScrollSubscription = Subscriptions.empty()

  fun bind(view: MoviesView) {
    checkNotNull(view) { "Set a view before doing anything else in this presenter" }

    movieSubscription = getMovies.get().subscribe(MoviesSubscriber(view))

    infiniteScrollSubscription = view.paginationEvents().subscribe { page ->
      movieSubscription.unsubscribe()
      movieSubscription = getMovies.get(page).subscribe(MoviesSubscriber(view))
    }
  }

  fun unbind() {
    movieSubscription.unsubscribe()
    infiniteScrollSubscription.unsubscribe()
  }

  private class MoviesSubscriber(val view: MoviesView) : Subscriber<Movie.List>() {
    override fun onCompleted() = Unit
    override fun onError(e: Throwable) = view.showError()
    override fun onNext(movies: Movie.List) = view.showMovies(movies)
  }
}
