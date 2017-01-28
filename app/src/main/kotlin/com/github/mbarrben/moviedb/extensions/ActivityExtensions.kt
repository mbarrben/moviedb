package com.github.mbarrben.moviedb.extensions

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