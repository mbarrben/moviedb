package com.github.mbarrben.moviedb.detail

import androidx.navigation.fragment.navArgs
import com.github.mbarrben.moviedb.commons.BaseFragment
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.databinding.DetailFragmentBinding

class DetailFragment : BaseFragment<DetailFragmentBinding>(R.layout.detail_fragment) {

    private val args: DetailFragmentArgs by navArgs()

    override fun onBind(binding: DetailFragmentBinding) {
        binding.movie = args.movie
    }

}
