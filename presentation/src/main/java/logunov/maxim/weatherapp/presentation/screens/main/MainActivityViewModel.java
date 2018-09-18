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

    private LocationFragment locationFragment = new LocationFragment();
    private HistoryFragment historyFragment = new HistoryFragment();
    private FragmentManager fragmentManager;

    public BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            showFragment(locationFragment);
                            return true;
                        case R.id.navigation_history:
                            showFragment(historyFragment);
                            return true;
                    }
                    return false;
                }
            };

    public MainActivityViewModel() {
        locationFragment.setRouter(router);
        historyFragment.setRouter(router);
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    private void showFragment(Fragment fragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        showFragment(locationFragment);
    }
}
