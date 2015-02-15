package com.github.mbarrben.moviedb.movies;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.github.mbarrben.moviedb.R;

public class MovieItemLayout extends CardView {

  @InjectView(R.id.movies_item_title) TextView title;

  public MovieItemLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    LayoutInflater.from(context).inflate(R.layout.movies_item_view, this);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.inject(this);
  }

  public void setTitle(CharSequence text) {
    title.setText(text);
  }
}
