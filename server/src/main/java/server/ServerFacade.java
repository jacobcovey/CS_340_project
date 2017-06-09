package server;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import server.model.GameInfo;
import server.model.ServerModelRoot;
import shared.classes.CommandData;
import shared.classes.Game;
import shared.classes.HistoryAction;
import shared.classes.Player;
import shared.classes.TrainCard;
import shared.classes.TrainCardColors;
import shared.classes.Turn;
import shared.classes.User;

public class ServerFacade {

    public static ServerFacade _instance = new ServerFacade();

    private ServerFacade() {}

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

    public List<Game> getFilteredGamesList() {
        List<Game> filteredGames = new ArrayList<>();
        for (Game g : getGameList()) {
            if (g.getPlayerLimit() > g.getPlayers().size()) {
                filteredGames.add(g);
            }
        }
        return filteredGames;
    }

    public void addGameInfo(Game game) {
        serverModelRoot.instance.addGameInfo(game);
    }

    public GameInfo getGameInfo(String gameId) {
        return serverModelRoot.instance.getGameInfo(gameId);
    }

    public void addCommandToUser(CommandData command, String userName) {
        serverModelRoot.addCommandToUser(command, userName);
    }

    public void addCommandToGame(CommandData command, String gameId) {
        GameInfo gameInfo = getGameInfo(gameId);
        for (Player player: gameInfo.getPlayers()) {
            addCommandToUser(command, player.getUserName());
        }
    }

    public List<CommandData> getCommandsForUser(String userName) {
        return serverModelRoot.getCommandsForUser(userName);
    }

    public void setNextTurn(GameInfo gameInfo, Player currentPlayer) {
        boolean isLastTurn = gameInfo.isLastTurn();
        if (isLastTurn && currentPlayer.getUserName().equals(gameInfo.getPlayerToTakeLasTurn().getUserName())) {
            gameOver(gameInfo);
        }
        boolean isNextPlayer = false;
        Player nextPlayer = null;
        for (Player player: gameInfo.getPlayers()) {
            if (isNextPlayer) {
                nextPlayer = player;
                break;
            }
            if (player.getUserName().equals(currentPlayer.getUserName())) {
                isNextPlayer = true;
            }
        }
        if (nextPlayer == null) {
            nextPlayer = gameInfo.getPlayers().get(0);
            if (gameInfo.getState() == GameInfo.State.FIRST_TURN) {
                gameInfo.setState(GameInfo.State.NOT_FIRST_TURN);
            }
            if (gameInfo.getState() == GameInfo.State.LAST_TURN) {
                gameInfo.setTurn(new Turn(nextPlayer.getUserName(), Turn.TurnState.LASTTURN));
                return;
            }
            gameInfo.setTurn(new Turn(nextPlayer.getUserName(), Turn.TurnState.BEGINNING));
        } else {
            if (gameInfo.getState() == GameInfo.State.FIRST_TURN) {
                gameInfo.setTurn(new Turn(nextPlayer.getUserName(), Turn.TurnState.FIRSTTURN));
                return;
            }
            if (gameInfo.getState() == GameInfo.State.LAST_TURN) {
                gameInfo.setTurn(new Turn(nextPlayer.getUserName(), Turn.TurnState.LASTTURN));
                return;
            }
            gameInfo.setTurn(new Turn(nextPlayer.getUserName(), Turn.TurnState.BEGINNING));
        }
    }

    private void gameOver(GameInfo gameInfo) {
        gameInfo.setState(GameInfo.State.GAME_OVER);
    }

    public void setLastTurn(GameInfo gameInfo, Player player) {
        gameInfo.setLastTurn(player);
    }

    public void addHistoryItemToGame(HistoryAction historyAction, GameInfo gameInfo, String gameId) {
        gameInfo.getHistory().addAction(historyAction);
        ServerFacade._instance.addCommandToGame(new CommandData(CommandData.Type.UPDATEHISTORY, historyAction), gameId);
    }

}
