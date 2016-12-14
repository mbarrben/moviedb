package com.github.mbarrben.moviedb

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mbarrben.moviedb.di.HasComponent

abstract class BaseFragment(@LayoutRes val layoutRes: Int) : Fragment() {

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    inject()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
      = inflater.inflate(layoutRes, container)

  // Override empty method to workaround lint bug wrongly stating that BaseFragment children who implements onDestroyView must call super
  // method even when they're actually calling it
  override fun onDestroyView() = super.onDestroyView()

  fun <T> getComponent(componentType: Class<T>): T = componentType.cast((activity as HasComponent<*>).component)

  abstract fun inject()

}
