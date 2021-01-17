package ro.mta.se.lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import ro.mta.se.lab.controllers.WeatherTodayController;
import ro.mta.se.lab.models.City;
import ro.mta.se.lab.utils.LoadCities;

import java.io.File;
import java.net.URL;
import java.util.List;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@NotNull Stage primaryStage) throws Exception {
        URL url = new File("src/main/java/ro/mta/se/lab/views/weatherTodayView.fxml").toURI().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent root = fxmlLoader.load();
        WeatherTodayController weatherTodayController = fxmlLoader.getController();

        List<City> cities = LoadCities.load("src/Cities.txt");
        weatherTodayController.setData(cities);
        weatherTodayController.setCountryComboBox();

        primaryStage.setTitle("Weather Today");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }
}
