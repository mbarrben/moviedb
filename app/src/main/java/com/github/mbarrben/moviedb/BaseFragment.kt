package com.github.mbarrben.moviedb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.github.mbarrben.moviedb.commons.inflateBinding

abstract class BaseFragment<T : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int
) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = inflater.inflateBinding<T>(layoutResId, container)
        onBind(binding)
        return binding.root
    }

    protected abstract fun onBind(binding: T)
}