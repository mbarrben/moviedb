package com.github.mbarrben.moviedb.commons

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.BindingAdapter

@BindingAdapter("goneUnless")
fun View.setGoneUnless(isVisible: Boolean) {
    visibility = if (isVisible) VISIBLE else GONE
}