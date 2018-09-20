package logunov.maxim.weatherapp.presentation.screens.main.fragments;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ObservableField;
import android.location.Address;
import android.location.Location;
import android.util.Log;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import logunov.maxim.domain.entity.Weather;
import logunov.maxim.domain.entity.WeatherRequest;
import logunov.maxim.domain.usecases.GetLocationUseCase;
import logunov.maxim.domain.usecases.GetReverseGeocodeUseCase;
import logunov.maxim.domain.usecases.GetWeatherUseCase;
import logunov.maxim.domain.usecases.InsertRequestUseCase;
import logunov.maxim.weatherapp.app.App;
import logunov.maxim.weatherapp.presentation.base.BaseViewModel;
import logunov.maxim.weatherapp.presentation.screens.main.MainActivityRouter;

public class LocationViewModel extends BaseViewModel<MainActivityRouter> {

    @Inject
    public GetLocationUseCase getLocationUseCase;

    @Inject
    public GetReverseGeocodeUseCase getReverseGeocodeUseCase;

    @Inject
    public GetWeatherUseCase getWeatherUseCase;

    @Inject
    public InsertRequestUseCase insertRequestUseCase;

    private double latitude = 0.0;
    private double longitude = 0.0;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    public ObservableField<String> latlong = new ObservableField<>("Location is loading...");
    public ObservableField<String> address = new ObservableField<>("Address is loading...");
    public ObservableField<String> weather = new ObservableField<>("Click on the top button to see the weather");


    public LocationViewModel() {
        getData();
    }

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    public void getData() {
        getLocationUseCase
                .getLocation()
                .subscribe(new Observer<Location>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(Location location) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        latlong.set(latitude + " , " + longitude);
                        getReverseGeocode();
                    }

                    @Override
                    public void onError(Throwable e) {
                        latlong.set(e.getLocalizedMessage());
                        address.set(e.getLocalizedMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getReverseGeocode() {
        getReverseGeocodeUseCase
                .getReverseGeocode(latitude, longitude)
                .subscribe(new Observer<List<Address>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(List<Address> addresses) {
                        String city = addresses.get(0).getLocality();
                        String country = addresses.get(0).getCountryName();
                        if (city == null) {
                            address.set(country);
                        } else {
                            address.set(addresses.get(0).getLocality()
                                    + ", "
                                    + addresses.get(0).getCountryName());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        address.set(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getWeather() {
        weather.set("Weather is loading...");
        getWeatherUseCase
                .getWeather(latitude, longitude)
                .subscribe(new Observer<Weather>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(Weather w) {
                        weather.set(w.toString());
                        insertRequest();
                    }

                    @Override
                    public void onError(Throwable e) {
                        weather.set(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void insertRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                insertRequestUseCase
                        .insert(new WeatherRequest(latitude,
                                longitude,
                                address.get(),
                                weather.get(),
                                dateFormat.format(new Date())));
            }
        }).start();
    }
}
