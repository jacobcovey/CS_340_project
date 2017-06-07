package com.example.jacobcovey.game_board;

import android.graphics.Color;
import android.graphics.PointF;
import android.util.Log;

import java.util.List;
import java.util.UUID;

import shared.classes.Player;

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

    public Route(int size, int id, List<PointF> points) {
        mId = id;
        mSize = size;
        mPoints = points;
        mColor = Color.TRANSPARENT;
        mCenterPoint = calculateCenterPoint(points);
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
