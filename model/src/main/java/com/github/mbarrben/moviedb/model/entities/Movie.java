package com.github.mbarrben.moviedb.model.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Movie {

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("overview")
    private String overview;

    public Movie(String id, String title, String overview) {
        this.id = id;
        this.title = title;
        this.overview = overview;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public static class List extends ArrayList<Movie> {
    }
}
