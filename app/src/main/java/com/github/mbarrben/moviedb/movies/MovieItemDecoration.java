package com.github.mbarrben.moviedb.movies;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.github.mbarrben.moviedb.R;

public class MovieItemDecoration extends RecyclerView.ItemDecoration {

  private int insets;

  public MovieItemDecoration(Context context) {
    insets = context.getResources().getDimensionPixelSize(R.dimen.insets);
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    super.getItemOffsets(outRect, view, parent, state);
    outRect.set(insets, insets, insets, insets);
  }
}
