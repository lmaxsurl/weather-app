package logunov.maxim.data.net;

import io.reactivex.Observable;
import logunov.maxim.data.entity.WeatherResponse;
import logunov.maxim.domain.entity.Weather;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("weather")
    Observable<WeatherResponse> getWeather(@Query("lat") double lat,
                                   @Query("lon") double lon,
                                   @Query("appid") String appid);
}
