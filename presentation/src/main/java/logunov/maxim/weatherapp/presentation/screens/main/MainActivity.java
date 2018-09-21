package logunov.maxim.weatherapp.presentation.screens.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ncapdevi.fragnav.FragNavController;

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
        router.createBuilder(savedInstanceState);
        init();
    }

    private void init() {
        binding.navigation
                .setOnNavigationItemSelectedListener(router.onNavigationItemSelectedListener);
    }
}
