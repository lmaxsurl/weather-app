package logunov.maxim.domain.usecases;

import javax.inject.Inject;

import io.reactivex.Completable;
import logunov.maxim.domain.entity.Weather;
import logunov.maxim.domain.entity.WeatherRequest;
import logunov.maxim.domain.executors.PostExecutionThread;
import logunov.maxim.domain.repositories.WeatherDataSource;

public class AddRequestUseCase extends BaseUseCase {

    private WeatherDataSource weatherDataSource;

    @Inject
    public AddRequestUseCase(WeatherDataSource weatherDataSource) {
        this.weatherDataSource = weatherDataSource;
    }

    public void add(final WeatherRequest request) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                weatherDataSource
                        .addRequest(request);
            }
        }).start();
    }
}
