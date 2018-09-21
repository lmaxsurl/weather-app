package logunov.maxim.data.entity;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse implements DataModel {

    @SerializedName("main")
    private MainData main;

    @SerializedName("wind")
    private WindData wind;

    public MainData getMain() {
        return main;
    }

    public WindData getWind() {
        return wind;
    }

}
