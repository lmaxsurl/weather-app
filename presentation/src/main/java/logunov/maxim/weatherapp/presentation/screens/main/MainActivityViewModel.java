package logunov.maxim.weatherapp.presentation.screens.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import javax.inject.Inject;

import logunov.maxim.domain.entity.WeatherRequest;
import logunov.maxim.domain.usecases.DeleteRequestUseCase;
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
                            router.replaceFragment(locationFragment);
                            return true;
                        case R.id.navigation_history:
                            router.replaceFragment(historyFragment);
                            return true;
                    }
                    return false;
                }
            };

    @Override
    public void onStart() {
        super.onStart();
        locationFragment.setRouter(router);
        historyFragment.setRouter(router);
    }

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    public void getData() {
        locationFragment.getData();
    }

    public void showLocationFragment() {
        router.replaceFragment(locationFragment);
    }

    public void deleteRequest(WeatherRequest request){
        historyFragment.deleteRequest(request);
    }
}
