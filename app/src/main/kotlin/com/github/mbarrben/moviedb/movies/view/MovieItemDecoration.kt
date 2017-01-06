package com.github.mbarrben.moviedb.movies.view

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ItemDecoration
import android.support.v7.widget.RecyclerView.State
import android.view.View
import com.github.mbarrben.moviedb.R

class MovieItemDecoration(context: Context, val spanCount: Int) : ItemDecoration() {

  private val insets = context.resources.getDimensionPixelSize(R.dimen.insets)

  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State?) {
    val position = parent.getChildAdapterPosition(view)

    val left = if (isLeftMost(position)) 2 * insets else insets
    val top = if (isTopMost(position)) 2 * insets else insets
    val right = if (isRightMost(position)) 2 * insets else insets
    val bottom = insets

    outRect.set(left, top, right, bottom)
  }

  private fun isLeftMost(position: Int) = position % spanCount == 0

  private fun isTopMost(position: Int) = position < spanCount

  private fun isRightMost(position: Int) = position % spanCount == 1
}
