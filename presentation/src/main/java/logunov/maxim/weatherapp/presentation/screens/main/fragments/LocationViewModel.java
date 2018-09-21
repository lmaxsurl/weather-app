package logunov.maxim.weatherapp.presentation.screens.main.fragments;

import android.databinding.ObservableField;
import android.location.Address;
import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import logunov.maxim.domain.entity.Weather;
import logunov.maxim.domain.entity.WeatherRequest;
import logunov.maxim.domain.usecases.AddRequestUseCase;
import logunov.maxim.domain.usecases.GetLocationUseCase;
import logunov.maxim.domain.usecases.GetReverseGeocodeUseCase;
import logunov.maxim.domain.usecases.GetWeatherUseCase;
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
    public AddRequestUseCase addRequestUseCase;

    private final String LATLON_STUB = "Location is loading...";
    private final String ADDRESS_STUB = "Address is loading...";
    private final String WEATHER_STUB = "Weather is loading...";

    private double latitude = 0.0;
    private double longitude = 0.0;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    public ObservableField<String> latlong = new ObservableField<>(LATLON_STUB);
    public ObservableField<String> address = new ObservableField<>(ADDRESS_STUB);
    public ObservableField<String> weather = new ObservableField<>(WEATHER_STUB);
    private boolean isReady = false;

    private Consumer<Location> doOnNextLocation = new Consumer<Location>() {
        @Override
        public void accept(Location location) {
            setLocation(location);
            getReverseGeocode();
            getWeather();
        }
    };

    private Consumer<List<Address>> doOnNextGeocode = new Consumer<List<Address>>() {
        @Override
        public void accept(List<Address> addresses) {
            setAddress(addresses);
            clickEnable.set(true);
        }
    };

    private Consumer<Weather> doOnNextWeather = new Consumer<Weather>() {
        @Override
        public void accept(Weather w) {
            weather.set(w.toString());
            addRequest();
        }
    };

    public LocationViewModel() {
            getData();
    }

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    public void getData() {
        setOnLoadingState();
        getCompositeDisposable().add(
                getLocationUseCase
                        .getLocation()
                        .subscribe(doOnNextLocation, doOnError));
    }

    private void setOnLoadingState() {
        clickEnable.set(false);
        latlong.set(LATLON_STUB);
        address.set(ADDRESS_STUB);
        weather.set(WEATHER_STUB);
    }

    private void setLocation(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        latlong.set(latitude + " , " + longitude);
    }

    private void getReverseGeocode() {
        getCompositeDisposable().add(
                getReverseGeocodeUseCase
                        .getReverseGeocode(latitude, longitude)
                        .subscribe(doOnNextGeocode, doOnError));
    }

    private void getWeather() {
        getCompositeDisposable().add(
                getWeatherUseCase
                        .getWeather(latitude, longitude)
                        .subscribe(doOnNextWeather, doOnError));
    }

    private void addRequest() {
        addRequestUseCase
                .add(new WeatherRequest(latitude,
                        longitude,
                        address.get(),
                        weather.get(),
                        dateFormat.format(new Date())));
    }

    private void setAddress(List<Address> addresses) {
        String city = null, country = null;
        for (Address add : addresses) {
            if (add.getCountryName() != null && country == null)
                country = add.getCountryName();
            if (add.getLocality() != null) {
                city = add.getLocality();
                break;
            }
        }
        address.set(city == null ? country : city + ", " + country);
    }
}