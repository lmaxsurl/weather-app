package logunov.maxim.domain.usecases;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import logunov.maxim.domain.entity.Weather;
import logunov.maxim.domain.entity.WeatherRequest;
import logunov.maxim.domain.executors.PostExecutionThread;
import logunov.maxim.domain.repositories.WeatherDataSource;

public class GetRequestsUseCase extends BaseUseCase {

    private WeatherDataSource weatherDataSource;

    @Inject
    public GetRequestsUseCase(PostExecutionThread postExecutionThread, WeatherDataSource weatherDataSource) {
        super(postExecutionThread);
        this.weatherDataSource = weatherDataSource;
    }

    public Observable<List<WeatherRequest>> getRequests() {
        return weatherDataSource
                .getRequests()
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
