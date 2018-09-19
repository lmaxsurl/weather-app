package logunov.maxim.domain.repositories;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import logunov.maxim.domain.entity.WeatherRequest;

public interface WeatherRepository {

    Observable<String> getWeather(String postalCode, String countryCode);

    Observable<List<WeatherRequest>> getRequests();

    Completable addRequest(WeatherRequest weatherRequest);

}
