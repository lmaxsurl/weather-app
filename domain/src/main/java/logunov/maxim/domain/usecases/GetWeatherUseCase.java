package logunov.maxim.domain.usecases;

import javax.inject.Inject;

import io.reactivex.Observable;
import logunov.maxim.domain.entity.Weather;
import logunov.maxim.domain.executors.PostExecutionThread;
import logunov.maxim.domain.repositories.WeatherRepository;

public class GetWeatherUseCase extends BaseUseCase {

    private WeatherRepository weatherRepository;

    @Inject
    public GetWeatherUseCase(WeatherRepository weatherRepository,
                             PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.weatherRepository = weatherRepository;
    }

    public Observable<Weather> getWeather(double latitude, double longitude){
        return weatherRepository
                .getWeather(latitude, longitude)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
