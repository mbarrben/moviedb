package com.github.mbarrben.moviedb.commons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.github.mbarrben.moviedb.commons.extensions.inflateBinding
import org.rewedigital.katana.Component
import org.rewedigital.katana.KatanaTrait
import org.rewedigital.katana.Module

abstract class BaseFragment<Binding : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int,
    private val modules: List<Module> = emptyList()
) : Fragment(),
    KatanaTrait {

    override val component: Component by lazy {
        (activity as KatanaTrait).component + modules
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = inflater.inflateBinding<Binding>(layoutResId, container)
        onBind(binding)
        return binding.root
    }

    protected abstract fun onBind(binding: Binding)
}