package com.github.mbarrben.moviedb.domain.moviesDetail

import com.github.mbarrben.moviedb.domain.navigation.Navigator
import rx.lang.kotlin.plusAssign
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

class DetailPresenter @Inject constructor(val getDetails: GetDetails, val navigator: Navigator) {

  private var subscriptions = CompositeSubscription()

  fun bind(view: DetailView) {
    checkNotNull(view) { "Set a view before doing anything else in this presenter" }

    val movie = navigator.getMovie()

    view.render(movie)
    subscriptions += view.loaded().subscribe {
      subscriptions += getDetails.get(movie.id).subscribe { view.details(it) }
    }

  }

  fun unbind() = subscriptions.unsubscribe()

}