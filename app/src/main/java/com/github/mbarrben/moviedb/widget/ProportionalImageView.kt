package com.github.mbarrben.moviedb.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.res.use
import com.github.mbarrben.moviedb.R

class ProportionalImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :    AppCompatImageView(context, attrs, defStyleAttr) {

    private var proportionWidth: Int = 1
    private var proportionHeight: Int = 1

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.ProportionalImageView, 0, 0).use { typedArray ->
            val ratio: String = typedArray.getString(R.styleable.ProportionalImageView_ratio) ?: "1:1"
            try {
                val (w, h) = ratio.split(':').map { it.toInt() }
                proportionWidth = w
                proportionHeight = h
            } catch (e: NumberFormatException) {
                throw IllegalStateException("ratio must have a format \"Int:Int\", but was $ratio", e)
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth
        val height = (proportionHeight * width) / proportionWidth
        setMeasuredDimension(width, height)
    }
}