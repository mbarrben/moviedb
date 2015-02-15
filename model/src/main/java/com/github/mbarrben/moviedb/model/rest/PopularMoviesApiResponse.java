package com.github.mbarrben.moviedb.model.rest;

import com.google.gson.annotations.SerializedName;

import com.github.mbarrben.moviedb.model.entities.Movie;

public class PopularMoviesApiResponse {

    @SerializedName("results")
    private Movie.List results;
    @SerializedName("page")
    private Number page;
    @SerializedName("total_pages")
    private Number totalPages;
    @SerializedName("total_results")
    private Number totalResults;

    public Movie.List getResults() {
        return results;
    }

    public Number getPage() {
        return page;
    }

    public Number getTotalPages() {
        return totalPages;
    }

    public Number getTotalResults() {
        return totalResults;
    }
}
