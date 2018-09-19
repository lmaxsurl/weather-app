package logunov.maxim.data.entity;


import com.google.gson.annotations.SerializedName;

public class WindResponse implements DataModel {

    @SerializedName("speed")
    private double speed;

    @SerializedName("deg")
    private double degrees;

    public double getSpeed() {
        return speed;
    }

    public double getDegrees() {
        return degrees;
    }
}
