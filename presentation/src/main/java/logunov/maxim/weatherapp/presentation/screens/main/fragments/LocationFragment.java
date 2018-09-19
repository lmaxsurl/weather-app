package logunov.maxim.weatherapp.presentation.screens.main.fragments;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void getData(){
        viewModel.getData();
    }
}
