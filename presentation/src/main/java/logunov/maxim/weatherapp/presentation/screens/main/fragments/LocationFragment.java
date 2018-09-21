package logunov.maxim.weatherapp.presentation.screens.main.fragments;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;

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
        checkPermissions();
    }

    private void checkPermissions() {
        RxPermissions rxPermissions = new RxPermissions(this);
        if (!isGranted()) {
            getCompositeDisposable().add(rxPermissions
                    .request(Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION)
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) {
                            viewModel.getData();
                        }
                    }));
        }
    }

    private boolean isGranted(){
        return ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED;
    }
}
