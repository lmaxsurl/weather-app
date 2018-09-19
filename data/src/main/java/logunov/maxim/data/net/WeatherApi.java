package logunov.maxim.data.net;

import io.reactivex.Observable;
import logunov.maxim.data.entity.WeatherResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("weather")
    Observable<WeatherResponse> getWeather(@Query("zip") String zip, @Query("appid") String appid);
}
