package ro.mta.se.lab.models;

public class City {
    private final String Country;
    private final String Name;
    private final Coordinate coordinate;

    public City(String country, String name, Coordinate coordinate) {
        Country = country;
        Name = name;
        this.coordinate = coordinate;
    }

    public String getCountry() {
        return Country;
    }

    public String getName() {
        return Name;
    }


    public Coordinate getCoordinate() {
        return coordinate;
    }


    @Override
    public String toString() {
        return "City{" +
                "Country='" + Country + '\'' +
                ", Name='" + Name + '\'' +
                ", coordinate=" + coordinate +
                '}';
    }
}
