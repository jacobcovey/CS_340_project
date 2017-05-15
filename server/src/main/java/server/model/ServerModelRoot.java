package server.model;

import java.util.List;
import java.util.Set;

import shared.classes.Game;
import shared.classes.User;

public class ServerModelRoot {

    private Set<User> users;
    private List<Game> gameList;
    private Set<String> authTokens;
}
