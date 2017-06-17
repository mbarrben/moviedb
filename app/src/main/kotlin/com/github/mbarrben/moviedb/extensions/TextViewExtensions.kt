package com.github.mbarrben.moviedb.extensions

import android.support.v4.text.util.LinkifyCompat
import android.support.v4.text.util.LinkifyCompat.LinkifyMask
import android.text.util.Linkify.ALL
import android.widget.TextView

fun TextView.linkify(@LinkifyMask mask: Int = ALL) = LinkifyCompat.addLinks(this, mask)
