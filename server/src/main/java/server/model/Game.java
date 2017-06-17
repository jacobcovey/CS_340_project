package server.model;

import server.ServerFacade;
import shared.classes.User;

/**
 * Created by billrichards on 6/17/17.
 */

public class Game extends shared.classes.Game {
    private GameInfo gameInfo;
    private int commandsSaved;

    public Game(String name, int playerLimit, User owner) {
        super(name, playerLimit, owner);
        this.commandsSaved = 0;
    }

    public Game(shared.classes.Game game) {
        setId(game.getId());
        setName(game.getName());
        setOwner(game.getOwner());
        setPlayerLimit(game.getPlayerLimit());
        setPlayers(game.getPlayers());
        this.commandsSaved = 0;
        gameInfo = ServerFacade._instance.getGameInfo(game.getId());
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
