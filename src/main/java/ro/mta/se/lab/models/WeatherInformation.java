package ro.mta.se.lab.models;

import org.jetbrains.annotations.NotNull;
import ro.mta.se.lab.utils.Response;

public class WeatherInformation {
    private final Double longitude;
    private final Double latitude;
    private final String iconId;
    private final String description;
    private final Double temperature;
    private final Double pressure;
    private final Double humidity;
    private final Double windSpeed;

    public WeatherInformation(@NotNull Response response) {
        longitude = response.getCoord().getLon();
        latitude = response.getCoord().getLat();
        iconId = response.getWeather().get(0).getIcon();
        description = response.getWeather().get(0).getDescription();
        temperature = response.getMain().getTemp();
        pressure = response.getMain().getPressure();
        humidity = response.getMain().getHumidity();
        windSpeed = response.getWind().getSpeed();
    }

    public String getIconId() {
        return iconId;
    }

    public String getDescription() {
        return description;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Double getPressure() {
        return pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }


    @Override
    public String toString() {
        return "WeatherInformation{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", iconId='" + iconId + '\'' +
                ", description='" + description + '\'' +
                ", temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                '}';
    }
}
