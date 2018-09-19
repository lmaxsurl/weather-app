package logunov.maxim.weatherapp.presentation.screens.main;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import logunov.maxim.weatherapp.R;
import logunov.maxim.weatherapp.databinding.ActivityMainBinding;
import logunov.maxim.weatherapp.presentation.base.BaseMvvmActivity;
import logunov.maxim.weatherapp.presentation.base.BaseRouter;
import logunov.maxim.weatherapp.presentation.base.BaseViewModel;

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
        init();
    }

    private void init() {
        binding.navigation
                .setOnNavigationItemSelectedListener(viewModel.onNavigationItemSelectedListener);
        viewModel.setFragmentManager(getSupportFragmentManager());

        RxPermissions rxPermissions = new RxPermissions(this);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED){
            rxPermissions
                    .request(Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION)
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean)  {
                            viewModel.getData();
                            Toast.makeText(MainActivity.this, "DONE!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

}
