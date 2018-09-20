package logunov.maxim.weatherapp.presentation.screens.main;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.ncapdevi.fragnav.FragNavController;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;
import logunov.maxim.domain.entity.Weather;
import logunov.maxim.domain.entity.WeatherRequest;
import logunov.maxim.weatherapp.R;
import logunov.maxim.weatherapp.databinding.ActivityMainBinding;
import logunov.maxim.weatherapp.presentation.base.BaseMvvmActivity;

public class MainActivity extends BaseMvvmActivity<
        MainActivityViewModel,
        ActivityMainBinding,
        MainActivityRouter> {

    @Override
    protected MainActivityViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(MainActivityViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainActivityRouter provideRouter() {
        return new MainActivityRouter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        router.setBuilder(new FragNavController.Builder(savedInstanceState,
                getSupportFragmentManager(), R.id.container));
        init();
    }

    private void init() {
        binding.navigation
                .setOnNavigationItemSelectedListener(router.onNavigationItemSelectedListener);
        checkPermissions();
    }

    private void checkPermissions() {
        RxPermissions rxPermissions = new RxPermissions(this);
        if (isGranted()) {
            rxPermissions
                    .request(Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION)
                    .subscribe();
        }
    }

    private boolean isGranted(){
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED;
    }
}
