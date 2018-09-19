package logunov.maxim.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse implements DataModel {

    @SerializedName("main")
    private WeatherDataResponse main;

    @SerializedName("wind")
    private WindResponse wind;

    @SerializedName("description")
    private String description;

    public WeatherDataResponse getMain() {
        return main;
    }

    public WindResponse getWind() {
        return wind;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
