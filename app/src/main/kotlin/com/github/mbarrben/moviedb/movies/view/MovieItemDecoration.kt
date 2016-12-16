package com.github.mbarrben.moviedb.movies.view

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ItemDecoration
import android.support.v7.widget.RecyclerView.State
import android.view.View
import com.github.mbarrben.moviedb.R

class MovieItemDecoration(context: Context) : ItemDecoration() {

  private val insets = context.resources.getDimensionPixelSize(R.dimen.insets)

  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State?) {
    super.getItemOffsets(outRect, view, parent, state)
    outRect.set(insets, insets, insets, insets)
  }
}
