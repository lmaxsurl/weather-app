package logunov.maxim.device.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.location.LocationRequest;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import logunov.maxim.domain.entity.AppError;
import logunov.maxim.domain.entity.ErrorType;
import logunov.maxim.domain.repositories.LocationRepository;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;

@Singleton
public class LocationService {

    private ReactiveLocationProvider locationProvider;
    private LocationRequest request;
    private Context context;
    private final int MAX_RESULTS = 10;

    @Inject
    public LocationService(Context context) {
        this.context = context;
        locationProvider = new ReactiveLocationProvider(context);
        request = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }

    public Observable<Location> getLocation() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return Observable.error(new AppError("No permission!", ErrorType.NO_GPS_PERMISSION_ERROR));
        }
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (manager != null && !manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return Observable.error(new AppError("GPS is disabled", ErrorType.GPS_NOT_AVAILABLE_ERROR));
        }
        return locationProvider
                .getUpdatedLocation(request)
                .take(1);
    }

    public Observable<List<Address>> getReverseGeocode(Double latitude, Double longitude) {
        return locationProvider
                .getReverseGeocodeObservable(latitude, longitude, MAX_RESULTS)
                .take(1);
    }

}
