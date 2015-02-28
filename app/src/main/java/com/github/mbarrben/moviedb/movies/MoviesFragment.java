package com.github.mbarrben.moviedb.movies;

import android.os.Bundle;
import android.view.View;
import butterknife.InjectView;
import com.github.mbarrben.moviedb.BaseFragment;
import com.github.mbarrben.moviedb.R;
import com.github.mbarrben.moviedb.domain.movies.MoviesPresenter;
import javax.inject.Inject;

public class MoviesFragment extends BaseFragment {

  @Inject MoviesPresenter presenter;
  @InjectView(R.id.movies_view) MoviesViewLayout moviesView;

  @Override
  protected int getFragmentLayout() {
    return R.layout.movies_fragment;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    presenter.onViewCreated(moviesView);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    presenter.onDestroyView();
  }
}
