package server.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import server.ServerFacade;
import server.model.GameInfo;
import shared.classes.CommandData;
import shared.classes.DestinationCard;
import shared.classes.Player;
import shared.classes.Route;
import shared.interfaces.iCommand;

/**
 * Created by Dylan on 6/5/2017.
 */

public class GameOverCommand implements iCommand {
    String data;
    String gameId;
    String userName;

    public List<CommandData> execute() {
        int longestRoute = 0;
        Map<Player, Map<String, Integer>> playerPointsInfo = new HashMap<>();

        GameInfo gameInfo = ServerFacade._instance.getGameInfo(gameId);
        List<Player> players = gameInfo.getPlayers();

        Map<Player, List<Route>> playerRoutes = getPlayerRoutes(players);
        playerRoutes = attachAdjacentRoutes(playerRoutes, players);
        List<Player> longestRoutePlayers = getLongestRouteLength(playerRoutes, players);

        for (Player player : longestRoutePlayers) {
            player.setHasLongestRoad(true);
        }

        for (Player player : players) {
            player.addPlayerPoints();
        }

        ServerFacade._instance.addCommandToGame(new CommandData(CommandData.Type.UPDATEGAMEINFO, gameInfo), gameId);
        return null;
    }

    //LONGEST ROUTE POINTS
    //Maps each claimed Route to the associated Player
    public Map<Player, List<Route>> getPlayerRoutes(List<Player> players) {
        List<Route> allRoutes = ServerFacade._instance.getGameInfo(gameId).getRoutes();
        Map<Player, List<Route>> playerRoutes = new HashMap<>();
        for (Player player : players) {
            if (!playerRoutes.containsKey(player)) {
                playerRoutes.put(player, new ArrayList<Route>());
            }
            for (Route route : allRoutes) {
                if (route.getPlayer().getUserName().equals(player.getUserName())) {
                    playerRoutes.get(player).add(route);
                }
            }
        }
        return playerRoutes;
    }
    //Each Player's claimed Routes are connected when adjacent
    public Map<Player, List<Route>> attachAdjacentRoutes(Map<Player, List<Route>> playerRoutes, List<Player> players) {
        for (Player player : players) {
            for (Route parentRoute : playerRoutes.get(player)) {
                for (Route childRoute : playerRoutes.get(player)) {
                    if (parentRoute.getCity1().getName() != childRoute.getCity1().getName() || parentRoute.getCity2().getName() != childRoute.getCity2().getName()) {
                        if (parentRoute.getCity1().getName() == childRoute.getCity1().getName() || parentRoute.getCity2().getName() == childRoute.getCity2().getName() || parentRoute.getCity1().getName() == childRoute.getCity2().getName() || parentRoute.getCity2().getName() == childRoute.getCity1().getName()) {
                            playerRoutes.get(player).get(playerRoutes.get(player).indexOf(parentRoute)).addAdjacentRoutes(playerRoutes.get(player).get(playerRoutes.get(player).indexOf(childRoute)));
                        }
                    }
                }
            }
            playerRoutes.put(player, playerRoutes.get(player));
        }
        return playerRoutes;
    }
    //Finds Longest Route among all Players
    public List<Player> getLongestRouteLength(Map<Player,List<Route>> playerRoutes, List<Player> players) {
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
    public int calcLongestRouteEachPlayer(List<Route> playerRoutes) {
        int longestSingleRoute = 0;
        for (Route playerRoute : playerRoutes) {
            int longestRouteFromThisCity = calcLongestRouteEachRoute(playerRoute);
            if (longestSingleRoute < longestRouteFromThisCity){
                longestSingleRoute = longestRouteFromThisCity;
            }
        }
        return longestSingleRoute;
    }
    //Finds the Longest Route starting at a specific Route
    public int calcLongestRouteEachRoute(Route route) {
        int longestRoute = 0;
        Set<Route> visitedRoutes = new HashSet<>();
        List<Integer> routeLengths = new ArrayList<>();
        Stack<Route> currentPath = new Stack<>();
        visitedRoutes.add(route);
        currentPath.push(route);
        traverseUnvisitedChildRoute(new Stack<String>(), route, route.getAdjacentRoutes(), currentPath, routeLengths, visitedRoutes);
        return calculateLargestLength(routeLengths);
    }
    //Traverses the Connected Claimed Routes
    public void traverseUnvisitedChildRoute(Stack<String> cityPath, Route currentRoute, List<Route> routeChildren, Stack<Route> currentPath, List<Integer> routeLengths, Set<Route> visitedRoutes){
        for (Route routeChild : routeChildren) {
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
                        traverseUnvisitedChildRoute(cityPath, routeChild, routeChild.getAdjacentRoutes(), currentPath, routeLengths, visitedRoutes);
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
                    traverseUnvisitedChildRoute(cityPath, routeChild, routeChild.getAdjacentRoutes(), currentPath, routeLengths, visitedRoutes);
                }
            }
        }
        routeLengths.add(calculateCurrentLength(currentPath));
        currentPath.pop();
        currentPath.pop();
        if (cityPath.size() > 0) {
            cityPath.pop();
        }
    }
    //Calculate the Length given a Route Path
    public Integer calculateCurrentLength(Stack<Route> currentPath) {
        int length = 0;
        for (Route route : currentPath) {
            length += route.getLength();
        }
        return length;
    }
    //Finds the Largest Route among all possible Route Paths
    public int calculateLargestLength(List<Integer> routeLengths) {
        int largestLength = 0;
        for (int i : routeLengths) {
            if (largestLength < i) {
                largestLength = i;
            }
        }
        return largestLength;
    }


}
