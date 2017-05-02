package com.github.mbarrben.moviedb.navigation

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.ActivityOptionsCompat.makeSceneTransitionAnimation
import android.view.View
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.domain.navigation.Navigator
import com.github.mbarrben.moviedb.model.extensions.fromJson
import com.github.mbarrben.moviedb.extensions.to
import com.github.mbarrben.moviedb.model.entities.Movie
import com.github.mbarrben.moviedb.movieDetail.view.platform.DetailActivity
import com.github.mbarrben.moviedb.movies.view.MoviesViewLayout
import com.google.gson.Gson
import org.jetbrains.anko.find
import javax.inject.Inject

class NavigatorImpl @Inject constructor(val activity: Activity, val gson: Gson) : Navigator {

  override fun detail(movie: Movie) {
    val intent = Intent(activity, DetailActivity::class.java)
        .putExtra("movie", gson.toJson(movie))

    val options = ActivityOptionsCompat.makeBasic().apply {
      findMoviePosterView(movie)?.let {
        update(makeSceneTransitionAnimation(activity, it to "transition"))
      }
    }

    ActivityCompat.startActivity(activity, intent, options.toBundle())
  }

  override fun getMovie() = gson.fromJson<Movie>(activity.intent.getStringExtra("movie"))

  private fun findMoviePosterView(movie: Movie): View? = activity.find<MoviesViewLayout>(R.id.fragment_movies)
      .findMoviePosterView(movie)

}
