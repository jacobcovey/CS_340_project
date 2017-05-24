package shared.classes;

/**
 * Created by billrichards on 5/24/17.
 */

class ChatMessage {

    private String player;
    private String message;

    public ChatMessage(String player, String message) {
        this.player = player;
        this.message = message;
    }

    public String getPlayer() {
        return player;
    }

    public String getMessage() {
        return message;
    }

}
