package com.github.mbarrben.moviedb.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.github.mbarrben.moviedb.R;
import com.github.mbarrben.moviedb.model.entities.Movie;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

  private final Movie.List list;
  private final LayoutInflater inflater;

  public MoviesAdapter(Context context, Movie.List list) {
    this.list = list;
    this.inflater = LayoutInflater.from(context);
  }

  @Override
  public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    MovieItemLayout view = (MovieItemLayout) inflater.inflate(R.layout.movies_item, parent, false);
    return new MovieViewHolder(view);
  }

  @Override
  public void onBindViewHolder(MovieViewHolder holder, int position) {
    CharSequence title = list.get(position).getTitle();
    holder.view.setTitle(title);
  }

  @Override
  public int getItemCount() {
    return list.size();
  }

  public static class MovieViewHolder extends RecyclerView.ViewHolder {

    MovieItemLayout view;

    public MovieViewHolder(MovieItemLayout view) {
      super(view);
      this.view = view;
    }
  }
}
