package shared.classes;

import java.util.List;

/**
 * Created by billrichards on 5/24/17.
 */

public class Chat {

    private List<ChatMessage> messages;

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
