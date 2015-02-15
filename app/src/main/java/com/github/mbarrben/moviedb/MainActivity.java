package com.github.mbarrben.moviedb;


import com.github.mbarrben.moviedb.di.UIBus;
import com.github.mbarrben.moviedb.domain.GetMovies;
import com.github.mbarrben.moviedb.model.entities.Movie;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import android.os.Bundle;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    GetMovies getMovies;
    @Inject
    @UIBus
    Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected List<Object> getModules() {
        LinkedList<Object> modules = new LinkedList<>();
        modules.add(new UIModule());
        return modules;
    }

    @Override
    protected void onResume() {
        super.onResume();
        bus.register(this);
        getMovies.get();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    @Subscribe
    public void onMoviesReceived(Movie.List movies) {
        Log.d("Movida", "onMoviesReceived");
    }
}
