package com.github.mbarrben.moviedb.movies.view.adapter

enum class Type(val value: Int) {
  MOVIE(0), LOADING(1);

  companion object {
    fun from(value: Int) = when (value) {
      0    -> MOVIE
      else -> LOADING
    }
  }
}