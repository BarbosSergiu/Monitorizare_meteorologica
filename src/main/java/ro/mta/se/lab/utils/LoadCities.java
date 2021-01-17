package ro.mta.se.lab.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ro.mta.se.lab.models.City;
import ro.mta.se.lab.models.Coordinate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadCities {
    @NotNull
    public static List<City> load(String filename) {
        List<City> result = new ArrayList<>();

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] tokens = data.split("\\s");
                if (tokens.length == 4) {
                    result.add(new City(tokens[0],
                            tokens[1],
                            new Coordinate(Double.valueOf(tokens[2]), Double.valueOf(tokens[3]))));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    @NotNull
    public static List<String> selectCitiesFromCountry(@NotNull List<City> cities, String country) {
        List<String> result = new ArrayList<>();

        for (City city : cities) {
            if (city.getCountry().equals(country)) {
                result.add(city.getName());
            }
        }

        return result;
    }

    @NotNull
    public static List<String> selectCountries(@NotNull List<City> cities) {
        List<String> countries = new ArrayList<>();

        for (City city : cities) {
            if (!countries.contains(city.getCountry())) {
                countries.add(city.getCountry());
            }
        }

        return countries;
    }

    @Nullable
    public static Coordinate getCoordinate(@NotNull List<City> cities, String city, String country) {
        for (City c : cities) {
            if (c.getName().equals(city) && c.getCountry().equals(country)) {
                return c.getCoordinate();
            }
        }
        return null;
    }
}
