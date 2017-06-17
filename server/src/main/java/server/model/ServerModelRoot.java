package server.model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Map;

import server.commands.CommandManager;
import server.database.iDatabaseFactory;
import shared.classes.City;
import shared.classes.CommandData;
import shared.classes.Game;
import shared.classes.Player;
import shared.classes.PlayerColors;
import shared.classes.Turn;
import shared.classes.User;
import server.model.GameInfo;
import shared.interfaces.iCommand;
import shared.interfaces.iGameInfo;

public class ServerModelRoot {

    public static ServerModelRoot instance;

    public static ServerModelRoot getInstance() {
        if (instance == null) {
            instance = new ServerModelRoot();
        }
        return instance;
    }

    private ServerModelRoot() {
    }

    private iDatabaseFactory plugin;

    private Set<User> users = new HashSet<>();
    private List<Game> gameList = new ArrayList<>();
    private Set<String> authTokens = new HashSet<>();
    private Map<String, GameInfo> gameInfos = new HashMap<>();
    private Map<String,List<CommandData>> commandsForUser = new HashMap<>();
    private Integer resetCountLimit = null;

    public Integer getResetCountLimit() {
        return resetCountLimit;
    }

    public void setResetCountLimit(Integer resetCountLimit) {
        this.resetCountLimit = resetCountLimit;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    public Set<String> getAuthTokens() {
        return authTokens;
    }

    public void setAuthTokens(Set<String> authTokens) {
        this.authTokens = authTokens;
    }

    public void addGameInfo(Game game) {
            GameInfo gameInfo = new GameInfo(game);
            gameInfos.put(game.getId(), gameInfo);
    }

    public GameInfo getGameInfo(String gameId) {
        return gameInfos.get(gameId);
    }

    public void addCommandToUser(CommandData command, String userName) {
        if (!commandsForUser.containsKey(userName) || commandsForUser.get(userName) == null) {
            commandsForUser.put(userName, new ArrayList<CommandData>());
        }
        (commandsForUser.get(userName)).add(command);
    }

    public List<CommandData> getCommandsForUser(String userName) {
        return commandsForUser.get(userName);
    }

    public void restoreUsers(Set<User> allUsers) {

        users = allUsers;
    }
    public void restoreGames(List<Game> allGames) {
        gameList = allGames;
        for (Game game : allGames) {
            addGameInfo(game);
        }
    }
    public void runCommands(List<CommandData> allCommands) {
        CommandManager manager = new CommandManager();
        Gson gson = new Gson();
        for (CommandData commandData : allCommands) {
            iCommand command = manager.createCommand(commandData, gson.toJson(commandData));
            try {
                command.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setPlugin(iDatabaseFactory plugin) {
        this.plugin = plugin;
    }

    public iDatabaseFactory getPlugin() {
        return plugin;
    }
}
