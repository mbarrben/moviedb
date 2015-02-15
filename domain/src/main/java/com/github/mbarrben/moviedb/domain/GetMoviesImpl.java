package com.github.mbarrben.moviedb.domain;

import com.github.mbarrben.moviedb.common.RestBus;
import com.github.mbarrben.moviedb.common.UIBus;
import com.github.mbarrben.moviedb.model.MovieRepository;
import com.github.mbarrben.moviedb.model.entities.Movie;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class GetMoviesImpl implements GetMovies {

    private final MovieRepository repo;
    private final Bus uiBus;
    private final Bus restBus;

    public GetMoviesImpl(MovieRepository repo, @RestBus Bus restBus, @UIBus Bus uiBus) {
        this.repo = repo;
        this.uiBus = uiBus;
        this.restBus = restBus;
    }

    @Override
    public void get() {
        restBus.register(this);
        repo.getMovies();
    }

    @Subscribe
    public void OnMoviesReceived(Movie.List movies) {
        uiBus.post(movies);
        restBus.unregister(this);
    }
}
