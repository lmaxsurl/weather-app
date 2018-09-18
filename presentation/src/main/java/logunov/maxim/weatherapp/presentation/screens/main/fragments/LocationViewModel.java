package logunov.maxim.weatherapp.presentation.screens.main.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ObservableField;

import logunov.maxim.weatherapp.app.App;
import logunov.maxim.weatherapp.presentation.base.BaseViewModel;
import logunov.maxim.weatherapp.presentation.screens.main.MainActivityRouter;

public class LocationViewModel extends BaseViewModel<MainActivityRouter> {

    public ObservableField<String> latitude = new ObservableField<>("53");
    public ObservableField<String> longitude = new ObservableField<>("24");
    public ObservableField<String> address = new ObservableField<>("example address");
    public ObservableField<String> weather = new ObservableField<>("weather");

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }
}
