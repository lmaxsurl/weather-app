package logunov.maxim.domain.usecases;

import android.location.Address;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import logunov.maxim.domain.executors.PostExecutionThread;
import logunov.maxim.domain.repositories.LocationRepository;

public class GetReverseGeocodeUseCase extends BaseUseCase {

    private LocationRepository locationRepository;

    @Inject
    public GetReverseGeocodeUseCase(LocationRepository locationRepository,
                              PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.locationRepository = locationRepository;
    }

    public Observable<List<Address>> getReverseGeocode(Double latitude, Double longitude){
        return locationRepository
                .getReverseGeocode(latitude, longitude)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
