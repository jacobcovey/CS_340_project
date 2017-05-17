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

    public User authenticateUser(String username, String password) {
        Set<User> theUsers = getUsers();
        for (User u : theUsers) {
            if (username == u.getUsername()) {
                if (password == u.getPassword()) {
                    return u;
                }
            }
        }
        return null;
    }

    public User getUserByUserName(String userName, String password) {
        Set<User> theUsers = getUsers();
        for (User u : theUsers) {
            if (userName == u.getUsername()) {
                return u;
            }
        }
        return null;
    }
}
