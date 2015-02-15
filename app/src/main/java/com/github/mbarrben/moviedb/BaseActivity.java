package com.github.mbarrben.moviedb;

import android.app.Activity;
import android.os.Bundle;

import java.util.List;

import butterknife.ButterKnife;
import dagger.ObjectGraph;

public abstract class BaseActivity extends Activity {

    private ObjectGraph activityScopeGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        injectViews();
    }

    public void inject(Object object) {
        activityScopeGraph.inject(object);
    }

    protected abstract List<Object> getModules();

    private void injectDependencies() {
        MovieDbApp movieDbApp = (MovieDbApp) getApplication();
        List<Object> activityScopeModules = getModules();
        activityScopeModules.add(new ActivityModule(this));
        activityScopeGraph = movieDbApp.plus(activityScopeModules);
        inject(this);
    }

    private void injectViews() {
        ButterKnife.inject(this);
    }
}
