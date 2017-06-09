package shared.classes;

import com.google.gson.annotations.Expose;

/**
 * Created by billrichards on 5/18/17.
 */

public class GameRequest {

    @Expose
    private User user;
    @Expose
    private Game game;

    public GameRequest(User user, Game game) {
        this.user = user;
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public Game getGame() {
        return game;
    }
}
