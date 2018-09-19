package logunov.maxim.domain.entity;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;

public class WeatherRequest implements DomainModel {

    private int latitude;
    private int longitude;
    private String address;
    private String weather;
    private Date date;

    public WeatherRequest(int latitude, int longitude, String address, String weather, Date date) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.weather = weather;
        this.date = date;
    }

    public String getWeather() {
        return weather;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return dateFormat.format(date) +
                '\n' +
                address +
                " (" +
                latitude +
                " , " +
                longitude +
                ')';
    }


}
