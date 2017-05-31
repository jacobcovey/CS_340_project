package com.example.jacobcovey.game_board;

import android.graphics.Color;
import android.graphics.PointF;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Riley on 5/31/2017.
 */

class RouteLoader {

    public static final String TAG = "RouteLoader";

    public static List<Route> loadRoutes() {
        List<String> stringArray = addStringsToArray();

        //convert strings in array to routes
        //add routes to mroutes
        return convertToRoutes(stringArray);
    }

    private static List<Route> convertToRoutes(List<String> stringArray) {
        List<Route> output = new ArrayList<>();

        for (String s : stringArray) {
            //make a route
            String[] tempArray = s.split(" ");
            int size = Integer.parseInt(tempArray[0]);
            int color = Integer.parseInt(tempArray[1]);
            color = convertToColor(color);

            List<PointF> points = new ArrayList<>();
            for (int x = 2; x < tempArray.length; x++) {
                Float f1 = Float.parseFloat(tempArray[x]);
                Float f2 = Float.parseFloat(tempArray[x + 1]);
                PointF p1 = new PointF(f1, f2);
                points.add(p1);
                x++;

                Log.i(TAG, "created route object");
            }
            //load into array
            Route route = new Route(size, color, points);
            output.add(route);
        }
        return output;

    }

    private static List<String> addStringsToArray() {
        List<String> stringArray = new ArrayList<>();

        stringArray.add("3 3 190.0625 243.29688");
//        stringArray.add("3 500 600 800 400 3");
//        stringArray.add("3 500 600 800 400 3");
//        stringArray.add("3 500 600 800 400 3");
//        stringArray.add("3 500 600 800 400 3");
//        stringArray.add("3 500 600 800 400 3");
//        stringArray.add("3 500 600 800 400 3");
//        stringArray.add("3 500 600 800 400 3");
//        stringArray.add("3 500 600 800 400 3");
//        stringArray.add("3 500 600 800 400 3");
//        stringArray.add("3 500 600 800 400 3");
//
//
//        stringArray.add("3 500 600 800 400 3");

        return stringArray;
    }

    public static int convertToColor(int color) {
        switch (color){
            case 1:
                return Color.RED;
            case 2:
                return Color.BLUE;
            case 3:
                return Color.GREEN;
            case 4:
                return Color.YELLOW;
            case 5:
                return Color.BLACK;
            case 6:
                return Color.MAGENTA;
            case 7:
                return Color.GRAY;
            case 8:
                return Color.CYAN;
            case 9:
                return Color.WHITE;
            default:
                return Color.TRANSPARENT;

        }
    }

}
