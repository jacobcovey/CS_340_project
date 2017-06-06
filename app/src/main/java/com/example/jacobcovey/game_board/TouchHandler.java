package com.example.jacobcovey.game_board;

import android.graphics.PointF;
import android.util.Log;

import java.util.List;

/**
 * Created by Riley on 6/5/2017.
 */

public class TouchHandler {
    public static final String TAG = "TOUCHHANDLER";
    private PointF mPoint;

    public TouchHandler(PointF point) {
        mPoint = point;
    }

    public Route getClosestRoute(List<Route> routes) {
        Route min = routes.get(0);
        for (Route r : routes) {
            min = compareRouteForSmallest(min, r, mPoint);
            Log.i(TAG, "getClosestRoute: " + min.getCenterPoint() + "id: " + min.getId());
        }
        return min;
    }

    private Route compareRouteForSmallest(Route output, Route r, PointF point) {
        PointF oldClosest = output.getCenterPoint();
        PointF newClosest = r.getCenterPoint();
        float oldCompare;
        float newCompare;

        oldCompare = getDistanceForPoints(oldClosest.x, point.x) + getDistanceForPoints(oldClosest.y, point.y);
        newCompare = getDistanceForPoints(newClosest.x, point.x) + getDistanceForPoints(newClosest.y, point.y);

        if (oldCompare > newCompare) {
            return r;
        } else {
            return output;
        }
    }

    private Float getDistanceForPoints(float x, float x1) {
        Float output;
        output = Math.abs(x - x1);
        return output;
    }


}
