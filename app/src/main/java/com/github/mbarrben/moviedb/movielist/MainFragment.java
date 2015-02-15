package com.github.mbarrben.moviedb.movielist;

import android.os.Bundle;
import android.view.View;
import com.github.mbarrben.moviedb.BaseFragment;
import com.github.mbarrben.moviedb.R;
import javax.inject.Inject;

public class MainFragment extends BaseFragment {

  @Inject MoviesPresenter presenter;

  @Override
  protected int getFragmentLayout() {
    return R.layout.fragment_main;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    presenter.onViewCreated();
  }

  @Override
  public void onDestroyView() {
    presenter.onDestroyView();
    super.onDestroyView();
  }
}
