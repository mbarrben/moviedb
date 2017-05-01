package com.github.mbarrben.moviedb.domain.movies

import io.reactivex.Observable

interface MovieSearchView {
  fun queries(): Observable<CharSequence>
  fun close(): Observable<Unit>
}
