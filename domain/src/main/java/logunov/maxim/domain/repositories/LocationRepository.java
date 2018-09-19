package logunov.maxim.domain.repositories;

import android.location.Address;
import android.location.Location;

import java.util.List;

import io.reactivex.Observable;

public interface LocationRepository {

    Observable<Location> getCurrentLocation();

    Observable<List<Address>> getReverseGeocode(Double latitude, Double longitude);

}
