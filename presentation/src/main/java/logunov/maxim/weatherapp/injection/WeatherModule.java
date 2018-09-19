package logunov.maxim.weatherapp.injection;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import logunov.maxim.data.net.WeatherService;
import logunov.maxim.data.repositories.WeatherRepositoryImpl;
import logunov.maxim.device.location.LocationService;
import logunov.maxim.device.repositories.LocationRepositoryImpl;
import logunov.maxim.domain.repositories.LocationRepository;
import logunov.maxim.domain.repositories.WeatherRepository;

@Module
public class WeatherModule {

    @Provides
    @Singleton
    public WeatherService provideWeatherService(Context context){
        return new WeatherService(context);
    }

    @Provides
    @Singleton
    public WeatherRepository provideWeatherRepository(WeatherService weatherService){
        return new WeatherRepositoryImpl(weatherService);
    }

}
