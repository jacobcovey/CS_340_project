package shared.constants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import shared.classes.City;
import shared.classes.DestinationCard;
import shared.classes.TrainCard;
import shared.classes.TrainCardColors;

import static shared.classes.City.ATLANTA;
import static shared.classes.City.BOSTON;
import static shared.classes.City.CALGARY;
import static shared.classes.City.CHICAGO;
import static shared.classes.City.DALLAS;
import static shared.classes.City.DENVER;
import static shared.classes.City.DULUTH;
import static shared.classes.City.EL_PASO;
import static shared.classes.City.HELENA;
import static shared.classes.City.HOUSTON;
import static shared.classes.City.KANSAS_CITY;
import static shared.classes.City.LITTLE_ROCK;
import static shared.classes.City.LOS_ANGELES;
import static shared.classes.City.MIAMI;
import static shared.classes.City.MONTREAL;
import static shared.classes.City.NASHVILLE;
import static shared.classes.City.NEW_ORLEANS;
import static shared.classes.City.NEW_YORK;
import static shared.classes.City.OKLAHOMA_CITY;
import static shared.classes.City.PHOENIX;
import static shared.classes.City.PITTSBURG;
import static shared.classes.City.PORTLAND;
import static shared.classes.City.SALT_LAKE_CITY;
import static shared.classes.City.SANTA_FE;
import static shared.classes.City.SAN_FRANCISCO;
import static shared.classes.City.SAULT_ST_MARIE;
import static shared.classes.City.SEATTLE;
import static shared.classes.City.TORONTO;
import static shared.classes.City.VANCOUVER;
import static shared.classes.City.WINNIPEG;
import static shared.classes.TrainCardColors.BLACK;
import static shared.classes.TrainCardColors.BLUE;
import static shared.classes.TrainCardColors.GREEN;
import static shared.classes.TrainCardColors.ORANGE;
import static shared.classes.TrainCardColors.PURPLE;
import static shared.classes.TrainCardColors.RED;
import static shared.classes.TrainCardColors.WHITE;
import static shared.classes.TrainCardColors.WILD;
import static shared.classes.TrainCardColors.YELLOW;

/**
 * Created by billrichards on 5/24/17.
 */

public class Constants {
    public static final int NUMBEROFTRAINCARS = 45;
    public static final List<City> CITIES = initializeCities();

    public static final List<TrainCard> UNSHUFFLED_TRAINCARD_DECK = initalizeTrainCards();
    public static final List<DestinationCard> UNSUFFLED_DESTINATION_DECK = initializeDestinationCards();

    private static List<TrainCard> initalizeTrainCards() {
        List<TrainCard> cards = new ArrayList<>();
        Set<TrainCardColors> colors = new HashSet<TrainCardColors>();
        colors.add(WHITE);
        colors.add(BLACK);
        colors.add(RED);
        colors.add(ORANGE);
        colors.add(YELLOW);
        colors.add(GREEN);
        colors.add(BLUE);
        colors.add(PURPLE);
        int colorId = 0;
        for (TrainCardColors color : colors) {
            for (int i = 0; i < 12; i++) {
                cards.add(new TrainCard(String.valueOf(colorId), color));
                colorId++;
            }
        }
        for (int i = 0; i < 14; i++) {
            cards.add(new TrainCard(String.valueOf(colorId), WILD));
            colorId++;
        }
        return cards;
    }

    private static List<DestinationCard> initializeDestinationCards() {
        List<DestinationCard> cards = new ArrayList<>();
        int destinationId = 0;

        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(LOS_ANGELES), getCity(NEW_YORK), 21));
        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(DULUTH), getCity(HOUSTON), 8));
        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(SAULT_ST_MARIE), getCity(NASHVILLE), 8));
        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(NEW_YORK), getCity(ATLANTA), 6));
        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(PORTLAND), getCity(NASHVILLE), 17));

        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(VANCOUVER), getCity(MONTREAL), 20));
        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(DULUTH), getCity(EL_PASO), 10));
        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(TORONTO), getCity(MIAMI), 10));
        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(PORTLAND), getCity(PHOENIX), 11));
        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(DALLAS), getCity(NEW_YORK), 11));

        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(CALGARY), getCity(SALT_LAKE_CITY), 7));
        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(CALGARY), getCity(PHOENIX), 13));
        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(LOS_ANGELES), getCity(MIAMI), 20));
        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(WINNIPEG), getCity(LITTLE_ROCK), 11));
        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(SAN_FRANCISCO), getCity(ATLANTA), 17));

        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(KANSAS_CITY), getCity(HOUSTON), 5));
        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(LOS_ANGELES), getCity(CHICAGO), 16));
        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(DENVER), getCity(PITTSBURG), 11));
        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(CHICAGO), getCity(SANTA_FE), 9));
        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(VANCOUVER), getCity(SANTA_FE), 13));

        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(BOSTON), getCity(MIAMI), 12));
        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(CHICAGO), getCity(NEW_ORLEANS), 7));
        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(MONTREAL), getCity(ATLANTA), 9));
        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(SEATTLE), getCity(NEW_YORK), 22));
        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(DENVER), getCity(EL_PASO), 4));

        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(HELENA), getCity(LOS_ANGELES), 8));
        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(WINNIPEG), getCity(HOUSTON), 12));
        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(MONTREAL), getCity(NEW_ORLEANS), 13));
        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(SAULT_ST_MARIE), getCity(OKLAHOMA_CITY), 9));
        cards.add(new DestinationCard(String.valueOf(destinationId++), getCity(SEATTLE), getCity(LOS_ANGELES), 9));

        return cards;
    }

    private static City getCity(String name) {
        for (City city : CITIES) {
            if (city.getName() == name) {
                return city;
            }
        }
        return null;
    }

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
