package logunov.maxim.domain.usecases;

import javax.inject.Inject;

import logunov.maxim.domain.entity.WeatherRequest;
import logunov.maxim.domain.executors.PostExecutionThread;
import logunov.maxim.domain.repositories.WeatherDataSource;

public class DeleteRequestUseCase extends BaseUseCase {

    private WeatherDataSource weatherDataSource;

    @Inject
    public DeleteRequestUseCase(PostExecutionThread postExecutionThread, WeatherDataSource weatherDataSource) {
        super(postExecutionThread);
        this.weatherDataSource = weatherDataSource;
    }

    public void delete(final WeatherRequest request) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                weatherDataSource
                        .deleteRequest(request);
            }
        }).start();
    }
}
