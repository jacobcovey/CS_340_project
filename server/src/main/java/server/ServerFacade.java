package server;

import java.util.List;
import java.util.Set;

import server.model.ServerModelRoot;
import shared.classes.Game;
import shared.classes.User;

public class ServerFacade {

    ServerModelRoot serverModelRoot = ServerModelRoot.getInstance();

    // FIXME needs to be a list of commands
    public void executeCommand(List<String> commands) {
//        for (Command c : commands) {
//            c.execute();
//        }
    }

    public Set<User> getUsers() {
        return serverModelRoot.getUsers();
    }

    public List<Game> getGameList() {
        return serverModelRoot.getGameList();
    }

    public Set<String> getAuthTokens() {
        return serverModelRoot.getAuthTokens();
    }

    public void addUser(User user) {
        serverModelRoot.getUsers().add(user);
    }

    public void addGame(Game game) {
        serverModelRoot.getGameList().add(game);
    }

    public void addAuthToken(String token) {
        serverModelRoot.getAuthTokens().add(token);
    }
}
