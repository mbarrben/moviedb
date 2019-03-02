package com.github.mbarrben.moviedb.commons

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <T> LifecycleOwner.observe(data: LiveData<T>, crossinline action: (T) -> Unit) {
    data.observe(this, Observer<T> { it?.let(action) })
}

inline fun <reified T : ViewModel> FragmentActivity.buildViewModel(crossinline factory: () -> T): T =
    ViewModelProviders.of(this, getViewModelFactory(factory)).get(T::class.java)

inline fun <reified T : ViewModel> Fragment.buildViewModel(crossinline factory: () -> T): T =
    ViewModelProviders.of(this, getViewModelFactory(factory)).get(T::class.java)

inline fun <reified T : ViewModel> getViewModelFactory(crossinline factory: () -> T): ViewModelProvider.Factory =
    object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
    }