package logunov.maxim.weatherapp.injection;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import logunov.maxim.device.location.LocationService;
import logunov.maxim.device.repositories.LocationRepositoryImpl;
import logunov.maxim.domain.repositories.LocationRepository;

@Module
public class LocationModule {

    @Provides
    @Singleton
    public LocationService provideLocationService(Context context){
        return new LocationService(context);
    }

    @Provides
    @Singleton
    public LocationRepository provideLocationRepository(LocationService locationService){
        return new LocationRepositoryImpl(locationService);
    }

}
