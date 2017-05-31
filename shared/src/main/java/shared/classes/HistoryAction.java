package shared.classes;

/**
 * Created by billrichards on 5/24/17.
 */

public class HistoryAction {

    private String player;
    private String actionMessage; //This is the message that will be output on peoples history

    public HistoryAction(String player, String actionMessage) {
        this.player = player;
        this.actionMessage = actionMessage;
    }

    public String getPlayer() {
        return player;
    }

    public String getActionMessage() {
        return actionMessage;
    }

    @Override
    public String toString() {
        return player + " : " + actionMessage;
    }
}
