package com.github.mbarrben.moviedb.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.github.mbarrben.moviedb.R
import timber.log.Timber

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.tag("detail").d("movie detail: $args.movie")
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }
}
