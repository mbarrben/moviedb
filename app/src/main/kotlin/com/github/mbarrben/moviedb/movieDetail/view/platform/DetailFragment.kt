package com.github.mbarrben.moviedb.movieDetail.view.platform

import android.os.Bundle
import android.view.View
import com.github.mbarrben.moviedb.BaseFragment
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.domain.moviesDetail.DetailPresenter
import com.github.mbarrben.moviedb.movies.di.MovieDetailComponent
import kotlinx.android.synthetic.main.detail_fragment.detailLayout
import javax.inject.Inject

class DetailFragment : BaseFragment(R.layout.detail_fragment) {

  @Inject lateinit var presenter: DetailPresenter

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    presenter.bind(detailLayout)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    presenter.unbind()
  }

  override fun inject() = getComponent(MovieDetailComponent::class.java).inject(this)
}
