package com.github.mbarrben.moviedb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mbarrben.moviedb.commons.inflateBinding
import com.github.mbarrben.moviedb.databinding.FragmentMoviesBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MoviesFragment : Fragment() {

    private val view: MoviesView = MoviesView()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = inflater.inflateBinding<FragmentMoviesBinding>(R.layout.fragment_movies, container)
        view.onCreate(fragment = this, binding = binding)
        return binding.root
    }
}
