package com.github.mbarrben.moviedb.detail.ui.view

import androidx.navigation.fragment.navArgs
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.app.inject
import com.github.mbarrben.moviedb.commons.BaseFragment
import com.github.mbarrben.moviedb.databinding.DetailFragmentBinding
import com.github.mbarrben.moviedb.detail.di.detailModule

class DetailFragment : BaseFragment<DetailFragmentBinding>(
    layoutResId = R.layout.detail_fragment,
    modules = listOf(detailModule)
) {

    private val args: DetailFragmentArgs by navArgs()
    private val detailView: DetailView by inject()

    override fun onBind(binding: DetailFragmentBinding) {
        detailView.onCreate(
            fragment = this,
            binding = binding,
            movie = args.movie
        )
    }

}
