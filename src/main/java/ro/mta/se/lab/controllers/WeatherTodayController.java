package ro.mta.se.lab.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;
import ro.mta.se.lab.models.City;
import ro.mta.se.lab.models.WeatherInformation;
import ro.mta.se.lab.utils.*;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class WeatherTodayController {
    @FXML
    private ComboBox<String> countryComboBox;

    @FXML
    private ComboBox<String> cityComboBox;

    @FXML
    private Text descriptionText;

    @FXML
    private Text temperatureText;

    @FXML
    private Text pressureText;

    @FXML
    private Text humidityText;

    @FXML
    private Text windSpeedText;

    @FXML
    private ImageView iconImageView;

    private List<City> cities;
    private String currentCountry;
    private String currentCity;

    public void setData(List<City> cities) {
        this.cities = cities;
    }

    public void setCountryComboBox() {
        countryComboBox.getItems().clear();
        countryComboBox.getItems().addAll(LoadCities.selectCountries(cities));
        countryComboBox.getSelectionModel().selectFirst();
        setCityComboBox();
    }

    public void setCityComboBox() {
        cityComboBox.getItems().clear();
        cityComboBox.getItems().addAll(LoadCities.selectCitiesFromCountry(cities, currentCountry));
        cityComboBox.getSelectionModel().selectFirst();
    }

    private void setIconImageView(String iconId) {
        File file = new File("src/Icons/" + iconId + "@2x.png");
        Image image = new Image(file.toURI().toString());
        iconImageView.setImage(image);
    }

    private void setFields(@NotNull WeatherInformation weatherInformation) {
        descriptionText.setText(StringUtils.capitalize(weatherInformation.getDescription()));
        temperatureText.setText(weatherInformation.getTemperature().toString() + " \u00B0C");
        pressureText.setText(weatherInformation.getPressure().toString() + " hPa");
        humidityText.setText(weatherInformation.getHumidity().toString() + " %");
        windSpeedText.setText(weatherInformation.getWindSpeed().toString() + " m/s");
        setIconImageView(weatherInformation.getIconId());
    }

    @FXML
    public void initialize() {
        countryComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                currentCountry = countryComboBox.getSelectionModel().getSelectedItem();
                setCityComboBox();
            }
        });
        cityComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                currentCity = cityComboBox.getSelectionModel().getSelectedItem();
                try {
                    WeatherInformation weatherInformation = new WeatherInformation(JsonParser.parse(HttpRequest
                            .request(Objects.requireNonNull(LoadCities
                                    .getCoordinate(cities, currentCity, currentCountry)))));
                    Logger.Log(weatherInformation.toString());
                    setFields(weatherInformation);
                } catch (Exception ignored) {
                }
            }
        });
    }
}
