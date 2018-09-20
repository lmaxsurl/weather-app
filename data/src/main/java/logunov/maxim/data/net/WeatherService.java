package logunov.maxim.data.net;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import logunov.maxim.data.BuildConfig;
import logunov.maxim.data.entity.HttpError;
import logunov.maxim.data.entity.WeatherResponse;
import logunov.maxim.domain.entity.Weather;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class WeatherService {
    private WeatherApi weatherRestApi;
    private Gson gson;
    private final int TIMEOUT = 10;
    private static final String WEATHER_REQUEST_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String WEATHER_API_KEY="a6b12652f0cdee1b911329f9c42cee2b";

    @Inject
    public WeatherService() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient
                .Builder()
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS);

        if(BuildConfig.DEBUG){
            okHttpClientBuilder.addInterceptor(logging);
        }

        gson = new GsonBuilder()
                .create();

        this.weatherRestApi = new Retrofit
                .Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(WEATHER_REQUEST_URL)
                .client(okHttpClientBuilder.build())
                .build()
                .create(WeatherApi.class);
    }

    public Observable<WeatherResponse> getWeather(double latitude, double longitude) {
        return weatherRestApi
                .getWeather(latitude, longitude, WEATHER_API_KEY);
    }


}


