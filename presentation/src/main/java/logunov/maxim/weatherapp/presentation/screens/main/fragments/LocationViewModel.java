package logunov.maxim.weatherapp.presentation.screens.main.fragments;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ObservableField;
import android.location.Address;
import android.location.Location;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import logunov.maxim.domain.usecases.GetLocationUseCase;
import logunov.maxim.domain.usecases.GetReverseGeocodeUseCase;
import logunov.maxim.weatherapp.app.App;
import logunov.maxim.weatherapp.presentation.base.BaseViewModel;
import logunov.maxim.weatherapp.presentation.screens.main.MainActivityRouter;

public class LocationViewModel extends BaseViewModel<MainActivityRouter> {

    @Inject
    public GetLocationUseCase getLocationUseCase;

    @Inject
    public GetReverseGeocodeUseCase getReverseGeocodeUseCase;

    private Double latitude = 0.0;
    private Double longitude = 0.0;
    private String postalCode;
    public ObservableField<String> latlong = new ObservableField<>("Location is loading...");
    public ObservableField<String> address = new ObservableField<>("Address is loading...");
    public ObservableField<String> weather = new ObservableField<>("weather");

    public LocationViewModel() {
        getData();
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
                        address.set(addresses.get(0).getLocality()
                                + ", "
                                + addresses.get(0).getCountryName());
                        postalCode = addresses.get(0).getPostalCode();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    public void getWeather(){

    }
}
