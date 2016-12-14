package com.github.mbarrben.moviedb.extensions

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View = LayoutInflater.from(context)
    .inflate(layoutRes, this, attachToRoot)

fun ImageView.load(url: String,
                   requestCreator: RequestCreator = Picasso.with(context).load(url),
                   config: RequestCreator.() -> RequestCreator = { requestCreator }) {
  config(requestCreator).into(this)
}