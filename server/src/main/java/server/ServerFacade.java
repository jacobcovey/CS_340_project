package server;

import java.util.ArrayList;
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
        List<Game> filteredGames = new ArrayList<>();
        for (Game g : getGameList()) {
            if (g.getPlayers().size() < g.getPlayerLimit()) {
                filteredGames.add(g);
            }
        }
        return filteredGames;
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
            if (username.equals(u.getUsername())) {
                if (password.equals(u.getPassword())) {
                    return u;
                }
            }
        }
        return null;
    }

    public User getUserByUserName(String userName, String password) {
        for (User u : getUsers()) {
            if (userName.equals(u.getUsername())) {
                return u;
            }
        }
        return null;
    }
    public Game addUserToGame(Game game, User user) {
        Game g = getGameById(game.getId());
        if (g == null) {
            return null;
        }
        g.addPlayerToGame(user);
        return g;
    }

    public Game removeUserFromGame(Game game, User user) {
        Game g = getGameById(game.getId());
        if (g == null) {
            return null;
        }
        g.removePlayerFromGame(user);
        return g;
    }

    public Game getGameById(String id) {
        for (Game g : getGameList()) {
            if (g.getId().equals(id)) {
                return g;
            }
        }
        return null;
    }
}
