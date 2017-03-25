package com.github.mbarrben.moviedb.extensions

import android.content.ComponentCallbacks
import android.content.res.Configuration
import android.support.v4.app.FragmentActivity
import android.support.v4.app.SharedElementCallback
import android.view.View

fun FragmentActivity.onEnterTransitionEnd(f: () -> Unit) {
  setEnterSharedElementCallback(object : SharedElementCallback() {
    override fun onSharedElementEnd(sharedElementNames: MutableList<String>?,
                                    sharedElements: MutableList<View>?,
                                    sharedElementSnapshots: MutableList<View>?) {
      setEnterSharedElementCallback(object : SharedElementCallback() {})
      f()
    }
  })
}

fun FragmentActivity.onConfigurationChanged(f: () -> Unit): Unbinder {
  val callback: ComponentCallbacksAdapter = object : ComponentCallbacksAdapter() {
    override fun onConfigurationChanged(newConfig: Configuration?) {
      f()
    }
  }

  registerComponentCallbacks(callback)

  return object : Unbinder {
    override fun unbind() {
      unregisterComponentCallbacks(callback)
    }
  }
}

interface Unbinder {
  fun unbind()
}

private abstract class ComponentCallbacksAdapter : ComponentCallbacks {
  override fun onLowMemory() = Unit
  override fun onConfigurationChanged(newConfig: Configuration?) = Unit
}
