package server.commands;

import shared.classes.Game;
import shared.classes.User;

/**
 * Created by Riley on 5/17/2017.
 */

public class AddUserToGameCommand {
    private User mUser;
    private Game mGame;

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public Game getGame() {
        return mGame;
    }

    public void setGame(Game game) {
        mGame = game;
    }
}
