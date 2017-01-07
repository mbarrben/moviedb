package com.github.mbarrben.moviedb.domain.moviesDetail

import com.github.mbarrben.moviedb.domain.navigation.Navigator
import com.github.mbarrben.moviedb.model.entities.Movie
import rx.Subscriber
import rx.subscriptions.Subscriptions
import javax.inject.Inject

class DetailPresenter @Inject constructor(val getDetails: GetDetails, val navigator: Navigator) {

  private var detailSubscription = Subscriptions.empty()

  fun bind(view: DetailView) {
    checkNotNull(view) { "Set a view before doing anything else in this presenter" }

    val movie = navigator.getMovie()

    view.render(movie)

    detailSubscription = getDetails.get(movie.id).subscribe(DetailSubscriber(view))
  }

  fun unbind() = detailSubscription.unsubscribe()

  private class DetailSubscriber(val view: DetailView) : Subscriber<Movie.Details>() {
    override fun onCompleted() = Unit
    override fun onError(e: Throwable) = Unit
    override fun onNext(details: Movie.Details) = view.details(details)
  }
}