package com.github.mbarrben.moviedb.movies.view.widget

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.extensions.use


class ProportionalImageView(context: Context, attrs: AttributeSet) : AppCompatImageView(context, attrs) {

  private var proportionHeight = 1
  private var proportionWidth = 1

  init {
    context.theme.obtainStyledAttributes(attrs, R.styleable.ProportionalImageView, 0, 0).use {
      proportionHeight = getInteger(R.styleable.ProportionalImageView_proportionHeight, 1).coerceAtLeast(1)
      proportionWidth = getInteger(R.styleable.ProportionalImageView_proportionWidth, 1).coerceAtLeast(1)
    }
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    val width = measuredWidth
    val height = (proportionHeight * width) / proportionWidth
    setMeasuredDimension(width, height)
  }
}