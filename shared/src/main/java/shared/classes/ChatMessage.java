package shared.classes;

import com.google.gson.annotations.Expose;

/**
 * Created by billrichards on 5/24/17.
 */

public class ChatMessage {

    @Expose
    private String userName;
    @Expose
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
