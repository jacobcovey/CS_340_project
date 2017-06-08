package server.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import server.ServerFacade;
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

        List<Player> players = ServerFacade._instance.getGameInfo(gameId).getPlayers();

        //Claimed Route Points
        Map<Player, List<Route>> playerRoutes = getPlayerRoutes(players);
        calculateRoutePoints(players, playerRoutes);

        //Longest Route Points
        playerRoutes = attachAdjacentRoutes(playerRoutes, players);
        List<Player> longestRoutePlayers = getLongestRouteLength(playerRoutes, players);
        calculateLongestRoutePoints(longestRoutePlayers);

        //Destination Card Points
        calculateDestinationCardPoints(players);

        //Total Points
        calculateTotalPoints(players);

        Player winner = determineWinners(players);
        return null;
    }

    //CLAIMED ROUTE POINTS
    //Maps each claimed Route to the associated Player
    public Map<Player, List<Route>> getPlayerRoutes(List<Player> players) {
        List<Route> allRoutes = ServerFacade._instance.getGameInfo(gameId).getServerRoutes();
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
    //Calculates and adds claimed Route points for each Player
    public void calculateRoutePoints(List<Player> players, Map<Player, List<Route>> playerRoutes) {
        int pointsCounter;
        for (Player player : players) {
            pointsCounter = 0;
            List<Route> routes = playerRoutes.get(player);
            for (Route route : routes) {
                pointsCounter += route.getPoints();
            }
            player.addPointsInfo("Claimed_Route_Points", pointsCounter);
        }
    }

    //LONGEST ROUTE POINTS
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
    //Calculates and adds Longest Route points for each Player
    public void calculateLongestRoutePoints(List<Player> longestRoutePlayers) {
        for (Player player : longestRoutePlayers) {
            player.addPointsInfo("Longest_Route_Points", 10);
        }
    }

    //DESTINATIONS REACHED POINTS & UNREACHED DESTINATIONS POINTS
    //Calculates and adds Destinations Reached & Unreached Destinations points for each Player
    public void calculateDestinationCardPoints(List<Player> players) {
        int reachedPoints;
        int unreachedPoints;
        for (Player player : players) {
            reachedPoints = 0;
            unreachedPoints = 0;
            Set<DestinationCard> destinationCards = player.getDestinationCards();
            for (DestinationCard destinationCard : destinationCards) {
                if (destinationCard.isComplete()) {
                    reachedPoints += destinationCard.getPoints();
                }
                else {
                    unreachedPoints = unreachedPoints - destinationCard.getPoints();
                }
            }
            player.addPointsInfo("Destinations_Reached_Points", reachedPoints);
            player.addPointsInfo("Unreached_Destinations_Points", unreachedPoints);
        }
    }

    //TOTAL POINTS
    public void calculateTotalPoints(List<Player> players) {
        int totalPoints;
        for (Player player : players) {
            totalPoints = 0;
            for (int points : player.getPointsInfo().values()) {
                totalPoints += points;
            }
            player.addPointsInfo("Total_Points", totalPoints);
        }
    }

    //Find Winner
    public Player determineWinners(List<Player> players) {
        //Most Points
        int mostPoints = 0;
        List<Player> totalPointsWinners = new ArrayList<>();
        for (Player player : players) {
            if (mostPoints < player.getPointsInfo().get("Total_Points")) {
                mostPoints = player.getPointsInfo().get("Total_Points");
            }
        }
        for (Player player : players) {
            if (mostPoints == player.getPointsInfo().get("Total_Points")) {
                totalPointsWinners.add(player);
            }
        }
        if (totalPointsWinners.size() == 1) {
            return totalPointsWinners.get(0);
        }

        //Most Destination Tickets
        int destinationCount;
        int largestDestinationCount = 0;
        Map<Player, Integer> playersDestinationCounts = new HashMap<>();
        for (Player player : players) {
            destinationCount = 0;
            Set<DestinationCard> destinationCards = player.getDestinationCards();
            for (DestinationCard destinationCard : destinationCards) {
                if (destinationCard.isComplete()) {
                    destinationCount++;
                }
            }
            playersDestinationCounts.put(player, destinationCount);
            if (largestDestinationCount < destinationCount) {
                largestDestinationCount = destinationCount;
            }
        }
        List<Player> destinationCountWinners = new ArrayList<>();
        for (Player player : totalPointsWinners) {
            if (largestDestinationCount == playersDestinationCounts.get(player)) {
                destinationCountWinners.add(player);
            }
        }
        if (destinationCountWinners.size() == 1) {
            return destinationCountWinners.get(0);
        }

        //Longest Path Winner
        for (Player player : players) {
            if (player.getPointsInfo().get("Longest_Route_Points") == 10) {
                return player;
            }
        }
        return null;
    }
}
