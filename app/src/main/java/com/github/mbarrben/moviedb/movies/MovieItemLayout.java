package com.github.mbarrben.moviedb.movies;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.github.mbarrben.moviedb.R;
import com.github.mbarrben.moviedb.domain.movies.MovieView;
import com.github.mbarrben.moviedb.model.entities.Movie;
import com.squareup.picasso.Picasso;

public class MovieItemLayout extends CardView implements MovieView {

  @InjectView(R.id.movies_item_picture) ImageView picture;

  public MovieItemLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    LayoutInflater.from(context).inflate(R.layout.movies_item_view, this);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.inject(this);
  }

  @Override
  public void render(Movie movie) {
    picture.setContentDescription(movie.getTitle());
    Picasso.with(getContext())
        .load(movie.getPosterPath())
        .fit()
        .into(picture);
  }
}
