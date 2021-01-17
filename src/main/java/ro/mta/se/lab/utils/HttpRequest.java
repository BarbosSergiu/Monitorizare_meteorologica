package ro.mta.se.lab.utils;

import org.jetbrains.annotations.NotNull;
import ro.mta.se.lab.models.Coordinate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpRequest {

    private static final String API_KEY = "5912f6a1bbec4205c5d47a6259d7a800";
    private static final String DEFAULT_URL_STRING =
            "http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=metric&appid=%s";

    public static String request(@NotNull Coordinate coordinate) throws IOException {
        String output;
        String urlString = String.format(DEFAULT_URL_STRING,
                coordinate.getLatitude(),
                coordinate.getLongitude(),
                API_KEY);

        URL url = new URL(urlString);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setDoOutput(true);

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            output = response.toString();
        }

        return output;
    }

}
