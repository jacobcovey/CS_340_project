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
        FACEUPTRAINCARDPICKED,
        UPDATEFACEUPTRAINCARDDECK,
        DRAWFACEDOWNCARD,
        FACEDOWNTRAINCARDPICKED,
        DRAWDESTINATIONCARDS,
        DESTINATIONCARDDRAWN,
        PICKDESTINATIONCARDS,
        DESTINATIONCARDSPICKED,
        CLAIMROUTE,
        ROUTECLAIMED,
        SENDCHAT,
        UPDATECHAT,
        GETOUTSTANDINGCOMMANDS,
        ERROR
    }

    private Type type;
    private Object data;
    private String gameId;
    private String userName;

    public CommandData(Type type, Object data) {
        this.type = type;
        this.data = data;
    }

    public CommandData(Type type, Object data, String gameId, String userName) {
        this.type = type;
        this.data = data;
        this.gameId = gameId;
        this.userName = userName;
    }

    public Type getType() {
        return type;
    }

    public Object getData() {
        return data;
    }

    public String getGameId() {
        return gameId;
    }
    public String getUserName() {
        return userName;
    }
}
