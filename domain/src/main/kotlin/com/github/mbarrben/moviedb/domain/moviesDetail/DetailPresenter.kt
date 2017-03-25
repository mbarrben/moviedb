package com.github.mbarrben.moviedb.domain.moviesDetail

import com.github.mbarrben.moviedb.domain.navigation.Navigator
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class DetailPresenter @Inject constructor(val getDetails: GetDetails, val navigator: Navigator) {

  private var subscriptions = CompositeDisposable()

  fun bind(view: DetailView) {
    checkNotNull(view) { "Set a view before doing anything else in this presenter" }

    val movie = navigator.getMovie()

    view.render(movie)
    subscriptions += view.loaded().subscribe {
      subscriptions += getDetails.get(movie.id).subscribe { view.details(it) }
    }

  }

  fun unbind() = subscriptions.dispose()

}