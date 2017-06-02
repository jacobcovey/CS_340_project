package shared.constants;

import java.util.ArrayList;
import java.util.List;

import shared.classes.City;

/**
 * Created by billrichards on 5/24/17.
 */

public class Constants {
    public static final int NUMBEROFTRAINCARS = 45;
    public static final List<City> CITIES = initializeCities();

    private static List<City> initializeCities() {
        List<City> cities = new ArrayList<>();
        cities.add(new City("Vancouver"));
        cities.add(new City("Calgary"));
        cities.add(new City("Winnipeg"));
        cities.add(new City("Sault St. Marie"));
        cities.add(new City("Montreal"));
        cities.add(new City("Seattle"));
        cities.add(new City("Helena"));
        cities.add(new City("Duluth"));
        cities.add(new City("Toronto"));
        cities.add(new City("Boston"));
        cities.add(new City("New York"));
        cities.add(new City("Pittsburg"));
        cities.add(new City("Chicago"));
        cities.add(new City("Omaha"));
        cities.add(new City("Denver"));
        cities.add(new City("Kansas City"));
        cities.add(new City("Saint Louis"));
        cities.add(new City("Nashville"));
        cities.add(new City("Raleigh"));
        cities.add(new City("Washington"));
        cities.add(new City("Charleston"));
        cities.add(new City("Atlanta"));
        cities.add(new City("Oklahoma City"));
        cities.add(new City("Little Rock"));
        cities.add(new City("Miami"));
        cities.add(new City("New Orleans"));
        cities.add(new City("Houston"));
        cities.add(new City("Dallas"));
        cities.add(new City("El Paso"));
        cities.add(new City("Santa Fe"));
        cities.add(new City("Phoenix"));
        cities.add(new City("Los Angeles"));
        cities.add(new City("Las Vegas"));
        cities.add(new City("Salt Lake City"));
        cities.add(new City("Portland"));
        cities.add(new City("San Francisco"));
        return cities;
    }

}
