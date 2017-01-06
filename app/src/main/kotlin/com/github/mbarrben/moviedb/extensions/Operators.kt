package com.github.mbarrben.moviedb.extensions

infix fun <A, B> A.to(that: B): android.support.v4.util.Pair<A, B> = android.support.v4.util.Pair(this, that)