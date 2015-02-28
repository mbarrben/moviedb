package com.github.mbarrben.moviedb.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.github.mbarrben.moviedb.R;
import com.github.mbarrben.moviedb.model.entities.Movie;

public class MoviesViewLayout extends RelativeLayout implements MoviesView {

  @InjectView(R.id.movies_recycler) RecyclerView recyclerView;

  public MoviesViewLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    LayoutInflater.from(context).inflate(R.layout.movies_view, this);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.inject(this);
  }

  @Override
  public void showMovies(Movie.List movies) {
    MoviesAdapter adapter = new MoviesAdapter(getContext(), movies);
    recyclerView.setAdapter(adapter);
  }

  @Override
  public void showError() {

  }
}
