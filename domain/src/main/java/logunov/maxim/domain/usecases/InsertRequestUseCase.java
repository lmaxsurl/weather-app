package logunov.maxim.domain.usecases;

import javax.inject.Inject;

import io.reactivex.Completable;
import logunov.maxim.domain.entity.Weather;
import logunov.maxim.domain.entity.WeatherRequest;
import logunov.maxim.domain.executors.PostExecutionThread;
import logunov.maxim.domain.repositories.WeatherDataSource;

public class InsertRequestUseCase extends BaseUseCase {

    private WeatherDataSource weatherDataSource;

    @Inject
    public InsertRequestUseCase(PostExecutionThread postExecutionThread, WeatherDataSource weatherDataSource) {
        super(postExecutionThread);
        this.weatherDataSource = weatherDataSource;
    }

    public void insert(WeatherRequest request) {
        weatherDataSource
                .addRequest(request);
    }


}
