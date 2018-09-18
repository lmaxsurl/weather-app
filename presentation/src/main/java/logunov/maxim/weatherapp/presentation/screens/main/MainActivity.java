package logunov.maxim.weatherapp.presentation.screens.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

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
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.setFragmentManager(getSupportFragmentManager());
        viewModel.onStart();
    }
}
