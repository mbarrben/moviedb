package com.github.mbarrben.moviedb.commons.extensions

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import timber.log.Timber

@BindingAdapter("goneUnless")
fun View.setGoneUnless(isVisible: Boolean) {
    visibility = if (isVisible) VISIBLE else GONE
}

@BindingAdapter("url")
fun ImageView.setUrl(url: String?) {
    var time = System.currentTimeMillis()

//    Picasso.get()
//        .also {
//            Timber.d("imageloader get ${System.currentTimeMillis() - time} ms")
//            time = System.currentTimeMillis()
//        }
//        .load(url)
//        .also {
//            Timber.d("imageloader load ${System.currentTimeMillis() - time} ms")
//            time = System.currentTimeMillis()
//        }
//        .into(this)

    Glide.with(this)
        .also {
            Timber.d("imageloader with ${System.currentTimeMillis() - time} ms")
            time = System.currentTimeMillis()
        }
        .load(url)
        .also {
            Timber.d("imageloader load ${System.currentTimeMillis() - time} ms")
            time = System.currentTimeMillis()
        }
        .into(this)

    Timber.d("imageloader into ${System.currentTimeMillis() - time} ms")
}