package shared.classes;

/**
 * Created by billrichards on 5/24/17.
 */

public class ChatMessage {

    private String userName;
    private String message;

    public ChatMessage(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }

}
