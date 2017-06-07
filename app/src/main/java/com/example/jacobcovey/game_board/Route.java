package com.example.jacobcovey.game_board;

import android.graphics.Color;
import android.graphics.PointF;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import shared.classes.City;
import shared.classes.Player;
import shared.classes.TrainCardColors;

/**
 * Created by Riley on 5/31/2017.
 */

public class Route {
    public static final String TAG = "ROUTE";
    private int mId;
    private int mSize;
    private List<PointF> mPoints;
    private int mColor;
    private PointF mCenterPoint;
    private Player player;
    private boolean isClaimed;
    private City mCity1;
    private City mCity2;
    private TrainCardColors mOriginalColor;

    public Route(int size, int id, City city1, City city2, int originalColor, List<PointF> points) {


    }

    public Route(int size, int id, TrainCardColors color, String city1, String city2, String points) {
        mId = id;
        mSize = size;
        String[] tempArray = points.split(" ");
        mPoints = new ArrayList<>();

        for (int x = 0; x < tempArray.length; x++) {
            Float f1 = Float.parseFloat(tempArray[x]);
            Float f2 = Float.parseFloat(tempArray[x + 1]);
            PointF p1 = new PointF(f1, f2);
            mPoints.add(p1);
            x++;

        }

        mOriginalColor = color;
        mCity1 = new City(city1);
        mCity2 = new City(city2);
        mColor = Color.TRANSPARENT;
        mCenterPoint = calculateCenterPoint(mPoints);
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

//        Log.i(TAG, "calculateCenterPoint: " + xPoint + " " + yPoint);
        return new PointF(xPoint, yPoint);
    }

    public int getSize() {
        return mSize;
    }

    public void setSize(int size) {
        mSize = size;
    }


    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public int getId() {
        return mId;
    }

    public List<PointF> getPoints() {
        return mPoints;
    }

    public void setPoints(List<PointF> points) {
        mPoints = points;
    }

    public PointF getCenterPoint() {
        return mCenterPoint;
    }
}
