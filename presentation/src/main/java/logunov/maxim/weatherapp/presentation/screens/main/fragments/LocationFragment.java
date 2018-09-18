package logunov.maxim.weatherapp.presentation.screens.main.fragments;

import android.arch.lifecycle.ViewModelProviders;

import logunov.maxim.weatherapp.R;
import logunov.maxim.weatherapp.databinding.LocationFragmentBinding;
import logunov.maxim.weatherapp.presentation.base.BaseMvvmFragment;
import logunov.maxim.weatherapp.presentation.screens.main.MainActivityRouter;

public class LocationFragment extends BaseMvvmFragment<
        LocationViewModel,
        LocationFragmentBinding,
        MainActivityRouter> {

    @Override
    protected LocationViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(LocationViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.location_fragment;
    }


}
