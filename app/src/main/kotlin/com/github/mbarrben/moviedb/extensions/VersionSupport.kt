package com.github.mbarrben.moviedb.extensions

import android.os.Build.VERSION

inline fun supports(sdk: Int, code: () -> Unit) {
  if (VERSION.SDK_INT >= sdk) {
    code()
  }
}
