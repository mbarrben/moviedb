package com.github.mbarrben.moviedb.movielist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.github.mbarrben.moviedb.R;
import com.github.mbarrben.moviedb.model.entities.Movie;

public class MoviesViewLayout extends RelativeLayout implements MoviesView {

  @InjectView(R.id.movies_textview) TextView textView;

  public MoviesViewLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    LayoutInflater.from(context).inflate(R.layout.view_movies, this);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.inject(this);
  }

  @Override
  public void showMovies(Movie.List movies) {
    Movie movie = movies.get(0);
    textView.setText(movie.getTitle());
  }
}
