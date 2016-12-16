package com.github.mbarrben.moviedb.movies.view

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.github.mbarrben.moviedb.extensions.use

class MoviesRecyclerView(context: Context, attrs: AttributeSet) : RecyclerView(context, attrs) {

  private val manager: GridLayoutManager
  private var columnWidth = -1

  init {
    setHasFixedSize(true)
    addItemDecoration(MovieItemDecoration(getContext()))
    initColumnsWidth(context, attrs)
    manager = GridLayoutManager(getContext(), 1)
    layoutManager = manager
  }

  override fun onMeasure(widthSpec: Int, heightSpec: Int) {
    super.onMeasure(widthSpec, heightSpec)

    if (columnWidth > 0) {
      setSpanCount()
    }
  }

  private fun initColumnsWidth(context: Context, attrs: AttributeSet?) {
    checkNotNull(attrs) { "attrs can't be null" }

    val attrsArray = intArrayOf(android.R.attr.columnWidth)

    context.obtainStyledAttributes(attrs, attrsArray).use {
      columnWidth = getDimensionPixelSize(0, -1)
    }
  }

  private fun setSpanCount() {
    val spanCount = Math.max(1, measuredWidth / columnWidth)
    manager.spanCount = spanCount
  }
}
