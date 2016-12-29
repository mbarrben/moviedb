package com.github.mbarrben.moviedb.navigation

import android.app.Activity
import com.github.mbarrben.moviedb.domain.navigation.Navigator
import com.github.mbarrben.moviedb.extensions.fromJson
import com.github.mbarrben.moviedb.model.entities.Movie
import com.github.mbarrben.moviedb.movieDetail.DetailActivity
import com.google.gson.Gson
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class NavigatorImpl @Inject constructor(val activity: Activity, val gson: Gson) : Navigator {

  override fun detail(movie: Movie) = activity.startActivity<DetailActivity>("movie" to gson.toJson(movie))

  override fun getMovie() = gson.fromJson<Movie>(activity.intent.getStringExtra("movie"))
}