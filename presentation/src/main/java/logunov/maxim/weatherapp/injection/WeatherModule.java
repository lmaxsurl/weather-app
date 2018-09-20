package logunov.maxim.weatherapp.injection;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import logunov.maxim.data.db.AppDatabase;
import logunov.maxim.data.net.WeatherService;
import logunov.maxim.data.repositories.LocalWeatherDataSource;
import logunov.maxim.data.repositories.WeatherRepositoryImpl;
import logunov.maxim.device.location.LocationService;
import logunov.maxim.device.repositories.LocationRepositoryImpl;
import logunov.maxim.domain.repositories.LocationRepository;
import logunov.maxim.domain.repositories.WeatherDataSource;
import logunov.maxim.domain.repositories.WeatherRepository;

@Module
public class WeatherModule {

    @Provides
    @Singleton
    public WeatherService provideWeatherService(){
        return new WeatherService();
    }

    @Provides
    @Singleton
    public WeatherRepository provideWeatherRepository(WeatherService weatherService){
        return new WeatherRepositoryImpl(weatherService);
    }

    @Provides
    @Singleton
    public WeatherDataSource provideWeatherDataSource(Context context){
        AppDatabase database = AppDatabase.getInstance(context);
        return new LocalWeatherDataSource(database.weatherDAO());
    }

}
