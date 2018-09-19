package logunov.maxim.data.repositories;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import logunov.maxim.data.entity.WeatherDataResponse;
import logunov.maxim.data.entity.WeatherResponse;
import logunov.maxim.data.entity.WindResponse;
import logunov.maxim.data.net.WeatherService;
import logunov.maxim.domain.entity.WeatherRequest;
import logunov.maxim.domain.repositories.WeatherRepository;

public class WeatherRepositoryImpl implements WeatherRepository {

    private WeatherService weatherRestService;
    private final double FAHRENHEIT_DEGREE = 273.15;

    @Inject
    public WeatherRepositoryImpl(WeatherService weatherRestService) {
        this.weatherRestService = weatherRestService;
    }

    @Override
    public Observable<String> getWeather(String postalCode, String countryCode) {
        return weatherRestService.getWeather(postalCode, countryCode)
                .map(new Function<WeatherResponse, String>() {
                    @Override
                    public String apply(WeatherResponse weatherResponse) {
                        return mapWeatherResponse(weatherResponse);
                    }
                });
    }

    @Override
    public Observable<List<WeatherRequest>> getRequests() {
        return null;
    }

    @Override
    public Completable addRequest(WeatherRequest weatherRequest) {
        return null;
    }

    private String mapWeatherResponse(WeatherResponse weatherResponse){
        StringBuilder weather = new StringBuilder("");
        WeatherDataResponse weatherDataResponse = weatherResponse.getMain();
        weather.append("Temp: ")
                .append(weatherDataResponse.getTemp() - FAHRENHEIT_DEGREE)
                .append('\n')
                .append("Max/min temp: ")
                .append(weatherDataResponse.getMaxTemp() - FAHRENHEIT_DEGREE)
                .append('/')
                .append(weatherDataResponse.getMinTemp() - FAHRENHEIT_DEGREE)
                .append('\n')
                .append("Description: ")
                .append(weatherResponse.getDescription())
                .append('\n')
                .append("Humidity: ")
                .append(weatherDataResponse.getHumidity())
                .append('\n')
                .append("Pressure: ")
                .append(weatherDataResponse.getPressure())
                .append('\n');
        WindResponse windResponse = weatherResponse.getWind();
        weather.append("Wind speed: ")
                .append(windResponse.getSpeed())
                .append('\n')
                .append("Wind degrees: ")
                .append(windResponse.getDegrees());
        return weather.toString();
    }
}
