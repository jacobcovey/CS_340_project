package com.example.jacobcovey.game_board;

import android.graphics.PointF;

import java.util.List;
import java.util.UUID;

/**
 * Created by Riley on 5/31/2017.
 */

public class Route {
    private UUID mId;
    private int mSize;
    private List<PointF> mPoints;
    private int mColor;

    public Route(int size, int color, List<PointF> points) {
        mId = UUID.randomUUID();
        mSize = size;
        mPoints = points;
        mColor = color;
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

    public UUID getId() {
        return mId;
    }

    public List<PointF> getPoints() {
        return mPoints;
    }

    public void setPoints(List<PointF> points) {
        mPoints = points;
    }
}
