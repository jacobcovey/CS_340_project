package shared.classes;

import com.google.gson.annotations.Expose;

/**
 * Created by billrichards on 5/24/17.
 */

public class Turn {

    public enum TurnState {
        FIRSTTURN,
        BEGINNING,
        ONETRAINCARDSELECTED,
        DESTINATIONCARDSDRAWN,
        LASTTURN
    }

    @Expose
    private String player;
    @Expose
    private TurnState state;

    public Turn(String player, TurnState state) {
        this.player = player;
        this.state = state;
    }

    public String getPlayer() {
        return player;
    }

    public TurnState getState() {
        return state;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public void setState(TurnState state) {
        this.state = state;
    }
}
