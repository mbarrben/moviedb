package com.github.mbarrben.moviedb.commons.extensions

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("goneUnless")
fun View.setGoneUnless(isVisible: Boolean) {
    visibility = if (isVisible) VISIBLE else GONE
}

@BindingAdapter("url")
fun ImageView.setUrl(url: String) {
    Picasso.get()
        .load(url)
        .into(this)
}