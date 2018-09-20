package logunov.maxim.domain.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;

import static logunov.maxim.domain.entity.WeatherRequest.TABLE_NAME;

@Entity(tableName = TABLE_NAME, primaryKeys = {"latitude", "longitude"})
public class WeatherRequest implements DomainModel {

    public static final String TABLE_NAME = "weather_requests";

    private double latitude;
    private double longitude;
    private String address;
    private String weather;
    private String date;

    public WeatherRequest(double latitude, double longitude, String address, String weather, String date) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.weather = weather;
        this.date = date;
    }

    public String getWeather() {
        return weather;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return date +
                '\n' +
                address +
                " (" +
                latitude +
                " , " +
                longitude +
                ')';
    }


}
