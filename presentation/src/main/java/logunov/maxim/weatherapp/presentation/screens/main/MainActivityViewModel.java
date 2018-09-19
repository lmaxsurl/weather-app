package logunov.maxim.weatherapp.presentation.screens.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import logunov.maxim.weatherapp.R;
import logunov.maxim.weatherapp.app.App;
import logunov.maxim.weatherapp.presentation.base.BaseViewModel;
import logunov.maxim.weatherapp.presentation.screens.main.fragments.HistoryFragment;
import logunov.maxim.weatherapp.presentation.screens.main.fragments.LocationFragment;

public class MainActivityViewModel extends BaseViewModel<MainActivityRouter> {

    private final LocationFragment locationFragment = new LocationFragment();
    private final HistoryFragment historyFragment = new HistoryFragment();

    public BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            router.showFragment(locationFragment);
                            return true;
                        case R.id.navigation_history:
                            router.showFragment(historyFragment);
                            return true;
                    }
                    return false;
                }
            };

    public MainActivityViewModel() {

    }

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    public void getData(){
        locationFragment.getData();
    }

    public void showLocationFragment(){
        router.showFragment(locationFragment);
    }

}
