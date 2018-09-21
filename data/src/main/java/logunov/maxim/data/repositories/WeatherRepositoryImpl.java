package logunov.maxim.data.repositories;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import logunov.maxim.data.entity.WeatherResponse;
import logunov.maxim.data.net.WeatherService;
import logunov.maxim.domain.entity.Weather;
import logunov.maxim.domain.repositories.WeatherRepository;

public class WeatherRepositoryImpl implements WeatherRepository {

    private WeatherService weatherRestService;

    @Inject
    public WeatherRepositoryImpl(WeatherService weatherRestService) {
        this.weatherRestService = weatherRestService;
    }

    @Override
    public Observable<Weather> getWeather(double latitude, double longitude) {
        return weatherRestService
                .getWeather(latitude, longitude)
                .map(new Function<WeatherResponse, Weather>() {
                    @Override
                    public Weather apply(WeatherResponse weatherResponse) {
                        return mapWeatherResponse(weatherResponse);
                    }
                });
    }

    private Weather mapWeatherResponse(WeatherResponse weatherResponse){
        Weather weather = new Weather();
        weather.setTemperature(weatherResponse.getMain().getTemp());
        weather.setHumidity(weatherResponse.getMain().getHumidity());
        weather.setPressure(weatherResponse.getMain().getPressure());
        weather.setWindSpeed(weatherResponse.getWind().getSpeed());
        weather.setWindDegrees(weatherResponse.getWind().getDegrees());
        return weather;
    }
}
