package com.github.mbarrben.moviedb.domain.moviesDetail

import com.github.mbarrben.moviedb.domain.navigation.Navigator
import javax.inject.Inject

class DetailPresenter @Inject constructor(val navigator: Navigator) {

  fun bind(view: DetailView) {
    checkNotNull(view) { "Set a view before doing anything else in this presenter" }

    view.render(navigator.getMovie())
  }

  fun unbind() = Unit
}