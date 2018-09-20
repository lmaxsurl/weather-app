package logunov.maxim.domain.repositories;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import logunov.maxim.domain.entity.Weather;
import logunov.maxim.domain.entity.WeatherRequest;

public interface WeatherRepository {

    Observable<Weather> getWeather(double latitude, double longitude);

}
