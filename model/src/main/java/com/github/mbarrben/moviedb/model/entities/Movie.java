package com.github.mbarrben.moviedb.model.entities;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Movie {

  private static final String POSTER_PREFIX = "http://image.tmdb.org/t/p/w500";

  @SerializedName("id") private String id;
  @SerializedName("title") private String title;
  @SerializedName("poster_path") private String posterPath;

  public Movie(String id, String title, String posterPath) {
    this.id = id;
    this.title = title;
    this.posterPath = posterPath;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getPosterPath() {
    return POSTER_PREFIX + posterPath;
  }

  public static class List extends ArrayList<Movie> {
  }
}
