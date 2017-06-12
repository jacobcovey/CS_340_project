package com.example.jacobcovey.game_board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Riley on 6/12/2017.
 */

public class DblRouteHandler {
    public static void setDoubleRoutes(List<Route> routes) {
        int[] array1 = {1, 3, 5, 7, 26, 34, 42, 44, 46, 48, 50, 57, 58, 69, 74, 80, 82, 84, 87, 91, 94};
        int[] array2 = {2, 4, 6, 8, 27, 35, 43, 45, 47, 49, 51, 59, 60, 70, 75, 81, 83, 86, 88, 92, 95};

        for (int i : array1) {
            routes.get(i - 1).setCompanionRouteNumb(array2[i]);
        }

        for (int y : array2) {
            routes.get(y - 1).setCompanionRouteNumb(array1[y]);
        }


    }

}
