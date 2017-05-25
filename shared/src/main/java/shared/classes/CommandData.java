package shared.classes;

/**
 * Created by billrichards on 5/15/17.
 */

public class CommandData {

    public enum Type {
        LOGIN,
        REGISTER,
        JOINGAME,
        CREATEGAME,
        STARTGAME,
        LEAVEGAME,
        GAMEJOINED,
        GAMELEFT,
        GAMESTARTED,
        LOGINSUCCESSFUL,
        UPDATECURRENTGAME,
        UPDATEGAMELIST,
        PICKFACEUPCARD,
        DRAWFACEDOWNCARD,
        DRAWDESTINATIONCARDS,
        DESTINATIONCARDSPICKED,
        ERROR
    }

    private Type type;
    private Object data;

    public CommandData(Type type, Object data) {
        this.type = type;
        this.data = data;
    }

    public Type getType() {
        return type;
    }

    public Object getData() {
        return data;
    }


}
