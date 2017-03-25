package com.github.mbarrben.moviedb.extensions

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.LayoutManager
import com.jakewharton.rxbinding2.support.v7.widget.RecyclerViewScrollEvent
import com.jakewharton.rxbinding2.support.v7.widget.scrollEvents
import io.reactivex.Observable

fun RecyclerView.scrollToEndEvents(visibleThreshold: Int): Observable<RecyclerViewScrollEvent> = scrollEvents()
    .filter { it.isScrollingUp() }
    .filter { lastXItemsAreVisible(visibleThreshold) }

private fun RecyclerViewScrollEvent.isScrollingUp() = dy() > 0

private fun RecyclerView.lastXItemsAreVisible(x: Int) = with(layoutManager) {
  itemCount <= lastVisibleItemPosition() + x
}

private fun LayoutManager.lastVisibleItemPosition() = when (this) {
  is LinearLayoutManager -> findLastCompletelyVisibleItemPosition()
  else                   -> 0
}