package com.github.mbarrben.moviedb.domain.movies

sealed class MovieMode {
  object Popular : MovieMode()
  data class Search(val query: String) : MovieMode()
}
