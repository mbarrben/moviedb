package com.github.mbarrben.moviedb.movies.view.platform

import android.os.Bundle
import android.view.View
import com.github.mbarrben.moviedb.BaseFragment
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.domain.movies.MoviesPresenter
import com.github.mbarrben.moviedb.movies.di.MoviesListComponent
import kotlinx.android.synthetic.main.movies_fragment.moviesView
import javax.inject.Inject

class MoviesFragment : BaseFragment(R.layout.movies_fragment) {

  @Inject lateinit var presenter: MoviesPresenter

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    presenter.bind(moviesView)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    presenter.unbind()
  }

  override fun inject() = getComponent(MoviesListComponent::class.java).inject(this)
}
