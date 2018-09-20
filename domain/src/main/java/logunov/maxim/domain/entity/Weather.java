package logunov.maxim.domain.entity;

public class Weather {

    public static final double FAHRENHEIT_DEGREE = 273.15;

    private double temperature;
    private double pressure;
    private int humidity;
    private double windSpeed;
    private double windDegrees;

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDegrees(double windDegrees) {
        this.windDegrees = windDegrees;
    }

    @Override
    public String toString() {
        StringBuilder weather = new StringBuilder("");
        weather.append("Temperature: ")
                .append(temperature - FAHRENHEIT_DEGREE)
                .append('\n')
                .append("Pressure: ")
                .append(pressure)
                .append('\n')
                .append("Humidity: ")
                .append(humidity)
                .append("%\n")
                .append("Wind speed: ")
                .append(windSpeed)
                .append('\n')
                .append("Wind direction: ");
        double windDirection = windDegrees / 45;
        switch ((int) Math.round(windDirection)) {
            case 0:
                weather.append("North");
                break;
            case 1:
                weather.append("North-West");
                break;
            case 2:
                weather.append("West");
                break;
            case 3:
                weather.append("South-West");
                break;
            case 4:
                weather.append("South");
                break;
            case 5:
                weather.append("South-East");
                break;
            case 6:
                weather.append("East");
                break;
            case 7:
                weather.append("North-East");
                break;
        }
        return weather.toString();
    }
}
