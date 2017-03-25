package com.github.mbarrben.moviedb.extensions

import android.annotation.SuppressLint
import android.os.Build.VERSION_CODES.LOLLIPOP
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import com.github.mbarrben.moviedb.di.HasComponent
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View = LayoutInflater.from(context)
    .inflate(layoutRes, this, attachToRoot)

fun ImageView.load(picasso: Picasso = Picasso.with(context),
                   url: String,
                   requestCreator: RequestCreator = picasso.load(url),
                   config: RequestCreator.() -> RequestCreator = { requestCreator },
                   callback: () -> Unit = {}
) {
  config(requestCreator).into(
      this,
      object : Callback.EmptyCallback() {
        override fun onSuccess() = callback()
      }
  )
}

fun <T> View.getComponent(componentType: Class<T>): T = componentType.cast((context as HasComponent<*>).component)

@SuppressLint("NewApi")
fun View.transitionName(name: String) = supports(LOLLIPOP) { this.transitionName = name }

fun View.show() {
  visibility = VISIBLE
}

fun View.hide() {
  visibility = GONE
}

fun Collection<View>.show(): Unit = forEach(View::show)

fun Collection<View>.hide(): Unit = forEach(View::hide)