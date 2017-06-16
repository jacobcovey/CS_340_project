package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import server.model.GameInfo;
import server.model.ServerModelRoot;
import shared.classes.CommandData;
import shared.classes.Game;
import shared.classes.HistoryAction;
import shared.classes.Player;
import shared.classes.Route;
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
            return;
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


    public void setLastTurn(GameInfo gameInfo, Player player) {
        gameInfo.setLastTurn(player);
    }

    public void addHistoryItemToGame(HistoryAction historyAction, GameInfo gameInfo, String gameId) {
        gameInfo.getHistory().addAction(historyAction);
        ServerFacade._instance.addCommandToGame(new CommandData(CommandData.Type.UPDATEHISTORY, historyAction), gameId);
    }

    private void gameOver(GameInfo gameInfo) {
        gameInfo.setState(GameInfo.State.GAME_OVER);
        Map<Player, Map<String, Integer>> playerPointsInfo = new HashMap<>();

        List<Player> players = gameInfo.getPlayers();

        Map<Player, List<Route>> playerRoutes = getPlayerRoutes(players, gameInfo);
        playerRoutes = attachAdjacentRoutes(playerRoutes, players);
        List<Player> longestRoutePlayers = getLongestRouteLength(playerRoutes, players);

        for (Player player : longestRoutePlayers) {
            player.setHasLongestRoad(true);
        }

        for (Player player : players) {
            player.addPlayerPoints();
        }

    }

    //LONGEST ROUTE POINTS
    //Maps each claimed Route to the associated Player
    private Map<Player, List<Route>> getPlayerRoutes(List<Player> players, GameInfo gameInfo) {
        List<Route> allRoutes = gameInfo.getRoutes();
        Map<Player, List<Route>> playerRoutes = new HashMap<>();
        for (Player player : players) {
            if (!playerRoutes.containsKey(player)) {
                playerRoutes.put(player, new ArrayList<Route>());
            }
            for (Route route : allRoutes) {
                if (route.getIsClaimed() && route.getPlayer() != null) {
                    if (route.getPlayer().getUserName().equals(player.getUserName())) {
                        playerRoutes.get(player).add(route);
                    }
                }
            }
        }
        return playerRoutes;
    }
    //Each Player's claimed Routes are connected when adjacent
    private Map<Player, List<Route>> attachAdjacentRoutes(Map<Player, List<Route>> playerRoutes, List<Player> players) {
        for (Player player : players) {
            for (Route parentRoute : playerRoutes.get(player)) {
                for (Route childRoute : playerRoutes.get(player)) {
                    if (parentRoute.getCity1().getName() != childRoute.getCity1().getName() || parentRoute.getCity2().getName() != childRoute.getCity2().getName()) {
                        if (parentRoute.getCity1().getName() == childRoute.getCity1().getName() || parentRoute.getCity2().getName() == childRoute.getCity2().getName() || parentRoute.getCity1().getName() == childRoute.getCity2().getName() || parentRoute.getCity2().getName() == childRoute.getCity1().getName()) {
                            playerRoutes.get(player).get(playerRoutes.get(player).indexOf(parentRoute)).addAdjacentRoutes(playerRoutes.get(player).get(playerRoutes.get(player).indexOf(childRoute)).getId());
                        }
                    }
                }
            }
            playerRoutes.put(player, playerRoutes.get(player));
        }
        return playerRoutes;
    }
    //Finds Longest Route among all Players
    private List<Player> getLongestRouteLength(Map<Player,List<Route>> playerRoutes, List<Player> players) {
        int longestRoute = 0;
        Map<Player, Integer> playersLongestRoutes = new HashMap<>();
        //finds each players longest route
        for (Player player : players) {
            int playersLongestRoute = calcLongestRouteEachPlayer(playerRoutes.get(player));
            playersLongestRoutes.put(player, playersLongestRoute);
            if (longestRoute < playersLongestRoute) {
                longestRoute = playersLongestRoute;
            }
        }

        List<Player> longestRoutePlayers = new ArrayList<>();
        //determines which players have the longest routes
        for (Player player : players) {
            if (longestRoute == playersLongestRoutes.get(player)) {
                longestRoutePlayers.add(player);
            }
        }
        return longestRoutePlayers;
    }
    //Finds Longest Route for a Player
    private int calcLongestRouteEachPlayer(List<Route> playerRoutes) {
        int longestSingleRoute = 0;
        for (Route playerRoute : playerRoutes) {
            int longestRouteFromThisCity = calcLongestRouteEachRoute(playerRoute, playerRoutes);
            if (longestSingleRoute < longestRouteFromThisCity){
                longestSingleRoute = longestRouteFromThisCity;
            }
        }
        return longestSingleRoute;
    }
    //Finds the Longest Route starting at a specific Route
    private int calcLongestRouteEachRoute(Route route, List<Route> allRoutes) {
        int longestRoute = 0;
        Set<Route> visitedRoutes = new HashSet<>();
        List<Integer> routeLengths = new ArrayList<>();
        Stack<Route> currentPath = new Stack<>();
        visitedRoutes.add(route);
        currentPath.push(route);
        traverseUnvisitedChildRoute(allRoutes, new Stack<String>(), route, route.getAdjacentRoutes(), currentPath, routeLengths, visitedRoutes);
        return calculateLargestLength(routeLengths);
    }
    //Traverses the Connected Claimed Routes
    private void traverseUnvisitedChildRoute(List<Route> allRoutes, Stack<String> cityPath, Route currentRoute, List<Integer> routeChildren, Stack<Route> currentPath, List<Integer> routeLengths, Set<Route> visitedRoutes){
        for (Integer routeChildId : routeChildren) {
            Route routeChild = null;
            for (Route someRoute : allRoutes) {
                if (someRoute.getId() == routeChildId) {
                    routeChild = someRoute;
                }
            }
            if (!visitedRoutes.contains(routeChild)) {
                if (cityPath.size() > 0) {
                    if (!cityPath.peek().equals(routeChild.getCity1().getName()) && !cityPath.peek().equals(routeChild.getCity2().getName())) {
                        if (currentRoute.getCity1().getName().equals(routeChild.getCity1().getName()) || currentRoute.getCity1().getName().equals(routeChild.getCity2().getName())) {
                            cityPath.push(currentRoute.getCity1().getName());
                        }
                        else {
                            cityPath.push(currentRoute.getCity2().getName());
                        }
                        currentPath.push(routeChild);
                        visitedRoutes.add(routeChild);
                        traverseUnvisitedChildRoute(allRoutes, cityPath, routeChild, routeChild.getAdjacentRoutes(), currentPath, routeLengths, visitedRoutes);
                    }
                }
                else {
                    if (currentRoute.getCity1().getName().equals(routeChild.getCity1().getName()) || currentRoute.getCity1().getName().equals(routeChild.getCity2().getName())) {
                        cityPath.push(currentRoute.getCity1().getName());
                    }
                    else {
                        cityPath.push(currentRoute.getCity2().getName());
                    }
                    currentPath.push(routeChild);
                    visitedRoutes.add(routeChild);
                    traverseUnvisitedChildRoute(allRoutes, cityPath, routeChild, routeChild.getAdjacentRoutes(), currentPath, routeLengths, visitedRoutes);
                }
            }
        }
        routeLengths.add(calculateCurrentLength(currentPath));
        currentPath.pop();
        if (cityPath.size() > 0) {
            cityPath.pop();
        }
    }
    //Calculate the Length given a Route Path
    private Integer calculateCurrentLength(Stack<Route> currentPath) {
        int length = 0;
        for (Route route : currentPath) {
            length += route.getLength();
        }
        return length;
    }
    //Finds the Largest Route among all possible Route Paths
    private int calculateLargestLength(List<Integer> routeLengths) {
        int largestLength = 0;
        for (int i : routeLengths) {
            if (largestLength < i) {
                largestLength = i;
            }
        }
        return largestLength;
    }

//    private void restoreUsers(plugin.getUserDAO().read());
//    private void restoreGames(plugin.getGameDAO().read());
//    private void runCommands(plugin.getCommandDAO().read());
}
