package shared.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import shared.interfaces.iGameInfo;

public class Game {

    private String id;
    private String name;
    private int playerLimit;
    private User owner;
    private List<User> players;
    private GameInfo gameInfo;
    private int commandsSaved;


    public Game(String name, int playerLimit, User owner) {
        this.id = String.valueOf(UUID.randomUUID());
        this.name = name;
        this.playerLimit = playerLimit;
        this.owner = owner;
        this.players = new ArrayList<>();
        this.commandsSaved = 0;
    }

    public Game() {
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getPlayers() {
        return players;
    }

    public void setPlayers(List<User> players) {
        this.players = players;
    }

    public int getPlayerLimit() {
        return playerLimit;
    }

    public void setPlayerLimit(int playerLimit) {
        this.playerLimit = playerLimit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addPlayerToGame(User user) {
        this.players.add(user);
    }

    public void removePlayerFromGame(User user) {
        for (User u : players) {
            if (u.getUsername().equals(user.getUsername())) {
                players.remove(u);
                return;
            }
        }
    }

    public GameInfo getGameInfo() {
        return gameInfo;
    }
    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    public int getCommandsSaved() {
        return commandsSaved;
    }

    public void incramentComandsSaved() {
        this.commandsSaved++;
    }

    public void resetCommandsSaved() {
        this.commandsSaved = 0;
    }
}
