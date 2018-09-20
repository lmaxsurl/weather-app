package logunov.maxim.domain.repositories;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import logunov.maxim.domain.entity.Weather;
import logunov.maxim.domain.entity.WeatherRequest;

public interface WeatherDataSource {

    Observable<List<WeatherRequest>> getRequests();

    void addRequest(WeatherRequest request);

}
