package com.example.jacobcovey.game_board;

import android.graphics.Color;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

import shared.classes.City;
import shared.classes.Player;
import shared.classes.TrainCardColors;

/**
 * Created by Riley on 5/31/2017.
 */

public class Route {
    public static final String TAG = "ROUTE";
    private int id;
    private int length;
    private int points;
    private List<PointF> segments;
    private int playerColor;
    private PointF centerPoint;
    private Player player;
    private boolean isClaimed;
    private City city1;
    private City city2;
    private TrainCardColors routeColor;

    public Route(int size, int id, TrainCardColors routeColor, String city1, String city2, String segments) {
        this.id = id;
        length = size;
        setPointsBasedOnLength();
        String[] tempArray = segments.split(" ");
        this.segments = new ArrayList<>();

        for (int x = 0; x < tempArray.length; x++) {
            Float f1 = Float.parseFloat(tempArray[x]);
            Float f2 = Float.parseFloat(tempArray[x + 1]);
            PointF p1 = new PointF(f1, f2);
            this.segments.add(p1);
            x++;

        }

        this.routeColor = routeColor;
        this.city1 = new City(city1);
        this.city2 = new City(city2);
        this.playerColor = Color.TRANSPARENT;
        centerPoint = calculateCenterPoint(this.segments);
    }

    private PointF calculateCenterPoint(List<PointF> points) {
        float xPoint = 0;
        float yPoint = 0;

        for (PointF p : points) {
            xPoint = xPoint + p.x;
            yPoint = yPoint + p.y;
        }

        xPoint = xPoint / points.size();
        yPoint = yPoint / points.size();

        return new PointF(xPoint, yPoint);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }


    public int getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(int playerColor) {
        this.playerColor = playerColor;
    }

    public TrainCardColors getRouteColor() {
        return routeColor;
    }

    public int getId() {
        return id;
    }

    public List<PointF> getSegments() {
        return segments;
    }

    public void setSegments(List<PointF> segments) {
        this.segments = segments;
    }

    public PointF getCenterPoint() {
        return centerPoint;
    }

    public void setPointsBasedOnLength() {
        switch (length) {
            case 1:
                points = 1;
                return;
            case 2:
                points = 2;
                return;
            case 3:
                points = 4;
                return;
            case 4:
                points = 7;
                return;
            case 5:
                points = 10;
                return;
            case 6:
                points = 15;
                return;
        }
    }

    public void claim(Player player) {
        isClaimed = true;
        this.player = player;
        switch (player.getColor()) {
            case BLUE:
                playerColor = Color.BLUE;
                return;
            case BLACK:
                playerColor = Color.BLACK;
                return;
            case RED:
                playerColor = Color.RED;
                return;
            case GREEN:
                playerColor = Color.GREEN;
                return;
            case YELLOW:
                playerColor = Color.YELLOW;
                return;
        }
    }

    public Player getPlayer() { return player; }

    public String getCity1() {
        return city1.getName();
    }

    public String getCity2() {
        return city2.getName();
    }
}
