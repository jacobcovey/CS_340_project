package shared.classes;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by billrichards on 5/24/17.
 */

public class Chat {

    @Expose
    private List<ChatMessage> messages = new ArrayList<>();

    public Chat(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void addMessage(ChatMessage message) {
        messages.add(message);
    }
}
