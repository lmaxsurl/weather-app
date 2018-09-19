package logunov.maxim.domain.usecases;

import android.location.Address;
import android.location.Location;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import logunov.maxim.domain.executors.PostExecutionThread;
import logunov.maxim.domain.repositories.LocationRepository;

public class GetLocationUseCase extends BaseUseCase{

    private LocationRepository locationRepository;

    @Inject
    public GetLocationUseCase(LocationRepository locationRepository,
                              PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.locationRepository = locationRepository;
    }

    public Observable<Location> getLocation(){
        return locationRepository
                .getCurrentLocation()
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
