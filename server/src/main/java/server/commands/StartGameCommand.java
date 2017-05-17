package server.commands;

import shared.classes.Game;

/**
 * Created by Riley on 5/17/2017.
 */

public class StartGameCommand {
    private Game mGame;

    public Game getGame() {
        return mGame;
    }

    public void setGame(Game game) {
        mGame = game;
    }
}
