package com.github.mbarrben.moviedb.extensions

import android.content.res.TypedArray

fun TypedArray.use(function: TypedArray.() -> Unit) {
  try {
    function()
  } finally {
    recycle()
  }
}