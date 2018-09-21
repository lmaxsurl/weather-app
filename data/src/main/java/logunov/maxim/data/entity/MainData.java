package logunov.maxim.data.entity;

import com.google.gson.annotations.SerializedName;

public class MainData implements DataModel {

    @SerializedName("temp")
    private double temp;

    @SerializedName("pressure")
    private double pressure;

    @SerializedName("humidity")
    private int humidity;

    public double getTemp() {
        return temp;
    }

    public double getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }
}
