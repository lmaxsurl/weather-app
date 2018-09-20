package logunov.maxim.data.repositories;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import logunov.maxim.data.db.WeatherDAO;
import logunov.maxim.domain.entity.Weather;
import logunov.maxim.domain.entity.WeatherRequest;
import logunov.maxim.domain.repositories.WeatherDataSource;

public class LocalWeatherDataSource implements WeatherDataSource {

    private final WeatherDAO weatherDAO;

    public LocalWeatherDataSource(WeatherDAO weatherDAO) {
        this.weatherDAO = weatherDAO;
    }

    @Override
    public Observable<List<WeatherRequest>> getRequests() {
        return weatherDAO
                .getAll()
                .toObservable();
    }

    @Override
    public void addRequest(WeatherRequest request) {
        weatherDAO.insert(request);
    }

    @Override
    public void deleteRequest(WeatherRequest request) {
        weatherDAO.delete(request);
    }


}
