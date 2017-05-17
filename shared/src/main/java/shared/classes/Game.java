package shared.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game {

    private String id;
    private String name;
    private int playerLimit;
    private User owner;
    private List<User> players;

    public Game(String name, int playerLimit, User owner) {
        this.id = String.valueOf(UUID.randomUUID());
        this.name = name;
        this.playerLimit = playerLimit;
        this.owner = owner;
        this.players = new ArrayList<>();
        this.players.add(owner);
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

}
