package com.github.mbarrben.moviedb.extensions

import android.text.util.Linkify.ALL
import android.text.util.Linkify.addLinks
import android.widget.TextView

fun TextView.linkify(mask: Int = ALL) = addLinks(this, mask)
