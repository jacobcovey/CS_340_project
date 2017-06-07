package server.commands;

import com.sun.corba.se.spi.activation.Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import server.ServerFacade;
import server.model.GameInfo;
import shared.classes.City;
import shared.classes.CommandData;
import shared.classes.Game;
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

        List<Player> players = ServerFacade._instance.getGameInfo(gameId).getPlayers();

        Map<Player, List<Route>> playerRoutes = getPlayerRoutes(players);
        playerRoutes = attachAdjacentRoutes(playerRoutes, players);

        Map<Player, Integer> playersLongestRoutes = new HashMap<>();
        List<Player> winners = getLongestRouteLength(playersLongestRoutes, playerRoutes, players);

        return null;
    }

    //attaches all routes to the associated Player
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


    public List<Player> getLongestRouteLength(Map<Player, Integer> playerLongestRoutes, Map<Player,List<Route>> playerRoutes, List<Player> players) {
        int longestRoute = 0;
        //finds each players longest route
        for (Player player : players) {
            int playersLongestRoute = calcLongestRouteEachPlayer(playerRoutes.get(player));
            playerLongestRoutes.put(player, playersLongestRoute);
            if (longestRoute < playersLongestRoute) {
                longestRoute = playersLongestRoute;
            }
        }

        List<Player> winners = new ArrayList<>();
        //determines which players have the longest routes
        for (Player player : players) {
            if (longestRoute == playerLongestRoutes.get(player)) {
                winners.add(player);
            }
        }
        return winners;
    }

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

    public int calcLongestRouteEachRoute(Route route) {
        int longestRoute = 0;
        Set<Route> visitedRoutes = new HashSet<>();
        List<Integer> routeLengths = new ArrayList<>();
        Stack<Route> currentPath = new Stack<>();
        visitedRoutes.add(route);
        currentPath.push(route);
        traverseUnvisitedChildRoute(route.getAdjacentRoutes(), currentPath, routeLengths, visitedRoutes);
        return calculateLargestLength(routeLengths);
    }


    public void traverseUnvisitedChildRoute(List<Route> routeChildren, Stack<Route> currentPath, List<Integer> routeLengths, Set<Route> visitedRoutes){
        for (Route routeChild : routeChildren) {
            if (!visitedRoutes.contains(routeChild)) {
                currentPath.push(routeChild);
                visitedRoutes.add(routeChild);
                traverseUnvisitedChildRoute(routeChild.getAdjacentRoutes(), currentPath, routeLengths, visitedRoutes);
            }
        }
        routeLengths.add(calculateCurrentLength(currentPath));
        currentPath.pop();
    }

    public Integer calculateCurrentLength(Stack<Route> currentPath) {
        int length = 0;
        for (Route route : currentPath) {
            length += route.getLength();
        }
        return length;
    }

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
