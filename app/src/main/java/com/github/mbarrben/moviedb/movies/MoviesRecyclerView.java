package com.github.mbarrben.moviedb.movies;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class MoviesRecyclerView extends RecyclerView {

  private GridLayoutManager manager;
  private int columnWidth = -1;

  public MoviesRecyclerView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  @Override
  protected void onMeasure(int widthSpec, int heightSpec) {
    super.onMeasure(widthSpec, heightSpec);

    if (columnWidth <= 0) {
      return;
    }

    setSpanCount();
  }

  private void init(Context context, AttributeSet attrs) {
    setHasFixedSize(true);
    addItemDecoration(new MovieItemDecoration(getContext()));
    initColumnsWidth(context, attrs);
    manager = new GridLayoutManager(getContext(), 1);
    setLayoutManager(manager);
  }

  private void initColumnsWidth(Context context, AttributeSet attrs) {
    if (attrs == null) {
      return;
    }

    int[] attrsArray = {
        android.R.attr.columnWidth
    };

    TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);
    columnWidth = array.getDimensionPixelSize(0, -1);
    array.recycle();
  }

  private void setSpanCount() {
    int spanCount = Math.max(1, getMeasuredWidth() / columnWidth);
    manager.setSpanCount(spanCount);
  }
}
