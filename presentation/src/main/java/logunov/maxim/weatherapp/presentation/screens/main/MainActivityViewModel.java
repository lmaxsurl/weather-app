package logunov.maxim.weatherapp.presentation.screens.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import java.util.ArrayList;

import javax.inject.Inject;

import logunov.maxim.domain.entity.WeatherRequest;
import logunov.maxim.domain.usecases.DeleteRequestUseCase;
import logunov.maxim.weatherapp.R;
import logunov.maxim.weatherapp.app.App;
import logunov.maxim.weatherapp.presentation.base.BaseViewModel;
import logunov.maxim.weatherapp.presentation.screens.main.fragments.HistoryFragment;
import logunov.maxim.weatherapp.presentation.screens.main.fragments.LocationFragment;

public class MainActivityViewModel extends BaseViewModel<MainActivityRouter> {

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }
}
