package shared.classes;

/**
 * Created by billrichards on 5/15/17.
 */

public class CommandData {

    private String type;
    private Object data;

    public CommandData(String type, Object data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public Object getData() {
        return data;
    }


}
