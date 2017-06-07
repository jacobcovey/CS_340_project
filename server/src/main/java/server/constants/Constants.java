package server.constants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import server.model.ServerModelRoot;
import shared.classes.City;
import shared.classes.DestinationCard;
import shared.classes.Route;
import shared.classes.TrainCard;
import shared.classes.TrainCardColors;

import static shared.classes.City.ATLANTA;
import static shared.classes.City.BOSTON;
import static shared.classes.City.CALGARY;
import static shared.classes.City.CHARLESTON;
import static shared.classes.City.CHICAGO;
import static shared.classes.City.DALLAS;
import static shared.classes.City.DENVER;
import static shared.classes.City.DULUTH;
import static shared.classes.City.EL_PASO;
import static shared.classes.City.HELENA;
import static shared.classes.City.HOUSTON;
import static shared.classes.City.KANSAS_CITY;
import static shared.classes.City.LAS_VEGAS;
import static shared.classes.City.LITTLE_ROCK;
import static shared.classes.City.LOS_ANGELES;
import static shared.classes.City.MIAMI;
import static shared.classes.City.MONTREAL;
import static shared.classes.City.NASHVILLE;
import static shared.classes.City.NEW_ORLEANS;
import static shared.classes.City.NEW_YORK;
import static shared.classes.City.OKLAHOMA_CITY;
import static shared.classes.City.OMAHA;
import static shared.classes.City.PHOENIX;
import static shared.classes.City.PITTSBURG;
import static shared.classes.City.PORTLAND;
import static shared.classes.City.RALEIGH;
import static shared.classes.City.SALT_LAKE_CITY;
import static shared.classes.City.SANTA_FE;
import static shared.classes.City.SAN_FRANCISCO;
import static shared.classes.City.SAULT_ST_MARIE;
import static shared.classes.City.SEATTLE;
import static shared.classes.City.ST_LOUIS;
import static shared.classes.City.TORONTO;
import static shared.classes.City.VANCOUVER;
import static shared.classes.City.WASHINGTON;
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
import static shared.constants.Constants.CITIES;

/**
 * Created by billrichards on 6/2/17.
 */

public class Constants {

    public static final List<TrainCard> UNSHUFFLED_TRAINCARD_DECK = initalizeTrainCards();
    public static final List<DestinationCard> UNSUFFLED_DESTINATION_DECK = initializeDestinationCards();
    public static final List<Route> ROUTES = initializeRoutes();

    private static List<Route> initializeRoutes() {
        List<Route> routeArray = new ArrayList<>();

        routeArray.add(new Route(1, 1, WILD, SEATTLE, VANCOUVER));
        routeArray.add(new Route(1, 2, WILD, SEATTLE, VANCOUVER));
        routeArray.add(new Route(1, 3, WILD, SEATTLE, PORTLAND));
        routeArray.add(new Route(1, 4, WILD, SEATTLE, PORTLAND));
        routeArray.add(new Route(5, 5, GREEN, PORTLAND, SAN_FRANCISCO));
        routeArray.add(new Route(5, 6, PURPLE, PORTLAND, SAN_FRANCISCO));
        routeArray.add(new Route(3, 7, YELLOW, SAN_FRANCISCO, LOS_ANGELES));
        routeArray.add(new Route(3, 8, PURPLE, SAN_FRANCISCO, LOS_ANGELES));
        routeArray.add(new Route(2, 9, WILD, LOS_ANGELES, LAS_VEGAS));
        routeArray.add(new Route(3, 10, WILD, LOS_ANGELES, PHOENIX));
        routeArray.add(new Route(6, 11, BLACK, LOS_ANGELES, EL_PASO));
        routeArray.add(new Route(5, 12, WHITE, PHOENIX, DENVER));
        routeArray.add(new Route(3, 13, WILD, PHOENIX, SANTA_FE));
        routeArray.add(new Route(3, 14, WILD, PHOENIX, EL_PASO));
        routeArray.add(new Route(6, 15, BLUE, PORTLAND, SALT_LAKE_CITY));
        routeArray.add(new Route(5, 16, ORANGE, SAN_FRANCISCO, SALT_LAKE_CITY));
        routeArray.add(new Route(5, 17, WHITE, SAN_FRANCISCO, SALT_LAKE_CITY));
        routeArray.add(new Route(3, 18, ORANGE, LAS_VEGAS, SALT_LAKE_CITY));
        routeArray.add(new Route(3, 19, WILD, VANCOUVER, CALGARY));
        routeArray.add(new Route(4, 20, WILD, SEATTLE, CALGARY));
        routeArray.add(new Route(6, 21, YELLOW, SEATTLE, HELENA));
        routeArray.add(new Route(4, 22, WILD, CALGARY, HELENA));
        routeArray.add(new Route(6, 23, WHITE, CALGARY, WINNIPEG));
        routeArray.add(new Route(4, 24, BLUE, HELENA, WINNIPEG));
        routeArray.add(new Route(3, 25, PURPLE, SALT_LAKE_CITY, HELENA));
        routeArray.add(new Route(3, 26, RED, SALT_LAKE_CITY, DENVER));
        routeArray.add(new Route(3, 27, YELLOW, SALT_LAKE_CITY, DENVER));
        routeArray.add(new Route(4, 28, GREEN, HELENA, DENVER));
        routeArray.add(new Route(2, 29, WILD, DENVER, SANTA_FE));
        routeArray.add(new Route(2, 30, WILD, SANTA_FE, EL_PASO));
        routeArray.add(new Route(6, 31, ORANGE, HELENA, DULUTH));
        routeArray.add(new Route(5, 32, RED, HELENA, OMAHA));
        routeArray.add(new Route(4, 33, PURPLE, DENVER, OMAHA));
        routeArray.add(new Route(4, 34, BLACK, DENVER, KANSAS_CITY));
        routeArray.add(new Route(4, 35, ORANGE, DENVER, KANSAS_CITY));
        routeArray.add(new Route(4, 36, RED, DENVER, OKLAHOMA_CITY));
        routeArray.add(new Route(3, 37, BLUE, SANTA_FE, OKLAHOMA_CITY));
        routeArray.add(new Route(5, 38, YELLOW, EL_PASO, OKLAHOMA_CITY));
        routeArray.add(new Route(4, 39, RED, EL_PASO, DALLAS));
        routeArray.add(new Route(6, 40, GREEN, EL_PASO, HOUSTON));
        routeArray.add(new Route(4, 41, BLACK, WINNIPEG, DULUTH));
        routeArray.add(new Route(2, 42, WILD, DULUTH, OMAHA));
        routeArray.add(new Route(2, 43, WILD, DULUTH, OMAHA));
        routeArray.add(new Route(1, 44, WILD, OMAHA, KANSAS_CITY));
        routeArray.add(new Route(1, 45, WILD, OMAHA, KANSAS_CITY));
        routeArray.add(new Route(2, 46, WILD, KANSAS_CITY, OKLAHOMA_CITY));
        routeArray.add(new Route(2, 47, WILD, KANSAS_CITY, OKLAHOMA_CITY));
        routeArray.add(new Route(2, 48, WILD, OKLAHOMA_CITY, DALLAS));
        routeArray.add(new Route(2, 49, WILD, OKLAHOMA_CITY, DALLAS));
        routeArray.add(new Route(1, 50, WILD, DALLAS, HOUSTON));
        routeArray.add(new Route(1, 51, WILD, DALLAS, HOUSTON));
        routeArray.add(new Route(6, 52, WILD, WINNIPEG, SAULT_ST_MARIE));
        routeArray.add(new Route(3, 53, WILD, DULUTH, SAULT_ST_MARIE));
        routeArray.add(new Route(6, 54, PURPLE, DULUTH, TORONTO));
        routeArray.add(new Route(3, 55, RED, DULUTH, CHICAGO));
        routeArray.add(new Route(4, 56, BLUE, OMAHA, CHICAGO));
        routeArray.add(new Route(2, 57, BLUE, KANSAS_CITY, ST_LOUIS));
        routeArray.add(new Route(2, 58, GREEN, ST_LOUIS, CHICAGO));
        routeArray.add(new Route(2, 59, PURPLE, KANSAS_CITY, ST_LOUIS));
        routeArray.add(new Route(2, 60, WHITE, ST_LOUIS, CHICAGO));
        routeArray.add(new Route(2, 61, WILD, OKLAHOMA_CITY, LITTLE_ROCK));
        routeArray.add(new Route(2, 62, WILD, LITTLE_ROCK, ST_LOUIS));
        routeArray.add(new Route(2, 63, WILD, DALLAS, LITTLE_ROCK));
        routeArray.add(new Route(2, 64, WILD, HOUSTON, NEW_ORLEANS));
        routeArray.add(new Route(3, 65, GREEN, LITTLE_ROCK, NEW_ORLEANS));
        routeArray.add(new Route(5, 66, BLACK, SAULT_ST_MARIE, MONTREAL));
        routeArray.add(new Route(2, 67, WILD, SAULT_ST_MARIE, TORONTO));
        routeArray.add(new Route(4, 68, WHITE, CHICAGO, TORONTO));
        routeArray.add(new Route(3, 69, ORANGE, CHICAGO, PITTSBURG));
        routeArray.add(new Route(3, 70, BLACK, CHICAGO, PITTSBURG));
        routeArray.add(new Route(5, 71, GREEN, ST_LOUIS, PITTSBURG));
        routeArray.add(new Route(2, 72, WILD, ST_LOUIS, NASHVILLE));
        routeArray.add(new Route(3, 73, WHITE, LITTLE_ROCK, NASHVILLE));
        routeArray.add(new Route(4, 74, YELLOW, NEW_ORLEANS, ATLANTA));
        routeArray.add(new Route(4, 75, ORANGE, NEW_ORLEANS, ATLANTA));
        routeArray.add(new Route(6, 76, RED, NEW_ORLEANS, MIAMI));
        routeArray.add(new Route(3, 77, WILD, TORONTO, MONTREAL));
        routeArray.add(new Route(3, 78, BLUE, MONTREAL, NEW_YORK));
        routeArray.add(new Route(3, 79, BLACK, NASHVILLE, RALEIGH));
        routeArray.add(new Route(2, 80, WILD, MONTREAL, BOSTON));
        routeArray.add(new Route(2, 81, WILD, MONTREAL, BOSTON));
        routeArray.add(new Route(2, 82, YELLOW, BOSTON, NEW_YORK));
        routeArray.add(new Route(2, 83, RED, BOSTON, NEW_YORK));
        routeArray.add(new Route(2, 84, WHITE, NEW_YORK, PITTSBURG));
        routeArray.add(new Route(2, 85, WILD, TORONTO, PITTSBURG));
        routeArray.add(new Route(2, 86, GREEN, NEW_YORK, PITTSBURG));
        routeArray.add(new Route(2, 87, ORANGE, NEW_YORK, WASHINGTON));
        routeArray.add(new Route(2, 88, BLACK, NEW_YORK, WASHINGTON));
        routeArray.add(new Route(2, 89, WILD, PITTSBURG, WASHINGTON));
        routeArray.add(new Route(2, 90, WILD, PITTSBURG, RALEIGH));
        routeArray.add(new Route(2, 91, WILD, WASHINGTON, RALEIGH));
        routeArray.add(new Route(2, 92, WILD, WASHINGTON, RALEIGH));
        routeArray.add(new Route(2, 93, WILD, RALEIGH, CHARLESTON));
        routeArray.add(new Route(2, 94, WILD, RALEIGH, ATLANTA));
        routeArray.add(new Route(2, 95, WILD, RALEIGH, ATLANTA));
        routeArray.add(new Route(2, 96, WILD, ATLANTA, CHARLESTON));
        routeArray.add(new Route(1, 97, WILD, ATLANTA, NASHVILLE));
        routeArray.add(new Route(4, 98, YELLOW, NASHVILLE, PITTSBURG));
        routeArray.add(new Route(4, 99, PURPLE, CHARLESTON, MIAMI));
        routeArray.add(new Route(5, 100, BLUE, ATLANTA, MIAMI));

        return routeArray;
    }

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

}
