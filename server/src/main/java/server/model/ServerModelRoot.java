package server.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Map;

import shared.classes.City;
import shared.classes.CommandData;
import shared.classes.Game;
import shared.classes.Player;
import shared.classes.PlayerColors;
import shared.classes.Turn;
import shared.classes.User;
import server.model.GameInfo;
import shared.interfaces.iGameInfo;

public class ServerModelRoot {

    public static ServerModelRoot instance;

    public static ServerModelRoot getInstance() {
        if (instance == null) {
            instance = new ServerModelRoot();
        }
        return instance;
    }

    private ServerModelRoot() {
    }

    private Set<User> users = new HashSet<>();
    private List<Game> gameList = new ArrayList<>();
    private Set<String> authTokens = new HashSet<>();
    private Map<String, GameInfo> gameInfos = new HashMap<>();
    private Map<String,List<CommandData>> commandsForUser = new HashMap<>();
    private List<City> cities = new ArrayList<>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    public Set<String> getAuthTokens() {
        return authTokens;
    }

    public void setAuthTokens(Set<String> authTokens) {
        this.authTokens = authTokens;
    }

    public void addGameInfo(Game game) {
            GameInfo gameInfo = new GameInfo();
            List<PlayerColors> colors = new ArrayList<>();
            colors.add(PlayerColors.BLUE);
            colors.add(PlayerColors.RED);
            colors.add(PlayerColors.GREEN);
            colors.add(PlayerColors.YELLOW);
            colors.add(PlayerColors.BLACK);
            List<User> users = game.getPlayers();
            for (int i = 0; i < users.size(); i++) {
                gameInfo.addPlayer(users.get(i).getUsername(), colors.get(i));
            }
            gameInfo.setTurn(new Turn(users.get(0).getUsername(), Turn.TurnState.FIRSTTURN));
            gameInfos.put(game.getId(), gameInfo);
    }

    public GameInfo getGameInfo(String gameId) {
        return gameInfos.get(gameId);
    }

    public void addCommandToUser(CommandData command, String userName) {
        if (!commandsForUser.containsKey(userName)) {
            commandsForUser.put(userName, new ArrayList<CommandData>())
        }
        (commandsForUser.get(userName)).add(command);
    }

    public List<CommandData> getCommandsForUser(String userName) {
        return commandsForUser.get(userName);
    }

    public City getCity(String name) {
        for (City city : cities) {
            if (city.getName() == name) {
                return city;
            }
        }
        return null;
    }

    public void initializeCities () {
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
        return;
    }
}
