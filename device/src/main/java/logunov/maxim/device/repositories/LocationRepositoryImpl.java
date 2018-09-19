package logunov.maxim.device.repositories;

import android.location.Address;
import android.location.Location;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import logunov.maxim.device.location.LocationService;
import logunov.maxim.domain.repositories.LocationRepository;

public class LocationRepositoryImpl implements LocationRepository {

    private LocationService locationService;

    @Inject
    public LocationRepositoryImpl(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    public Observable<Location> getCurrentLocation() {
        return locationService
                .getLocation();
    }

    @Override
    public Observable<List<Address>> getReverseGeocode(Double latitude, Double longitude) {
        return locationService
                .getReverseGeocode(latitude, longitude);
    }
}
