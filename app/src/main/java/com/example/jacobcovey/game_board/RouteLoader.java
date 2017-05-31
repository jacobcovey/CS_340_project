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

//                Log.i(TAG, "created route object");
            }
            //load into array
            Route route = new Route(size, color, points);
            output.add(route);
        }
        return output;

    }

    private static List<String> addStringsToArray() {
        List<String> stringArray = new ArrayList<>();

        stringArray.add("1 3 193.0625 243.29688");
        stringArray.add("1 2 220.0625 243.29688");
        stringArray.add("1 4 163.0625 328.32812");
        stringArray.add("1 5 197.0625 345.34375");

        stringArray.add("5 1 132.0 426.39062 105.0 494.45312 98.0 561.4844 102.0 622.5156 115.0 700.5625");
        stringArray.add("5 2 159.0625 433.42188 136.0 490.42188 125.0 558.4844 132.0 626.5 139.0625 690.5781");

        stringArray.add("3 3 153.0625 802.6094 183.0625 859.65625 231.0625 914.6875");
        stringArray.add("3 4 173.0625 792.625 207.0625 846.6719 254.0625 903.6719");

        stringArray.add("2 1 305.125 880.65625 366.125 836.6406");
        stringArray.add("3 2 346.125 920.6875 407.125 917.6875 481.1875 924.71875");
        stringArray.add("6 3 342.125 975.71875 407.125 1012.75 464.1875 1029.7656 542.1875 1046.7812 610.25 1046.7812 681.25 1032.7656");

        stringArray.add("5 1 556.25 897.6719 583.25 846.6719 620.25 788.6406 664.25 744.5781 736.3125 707.59375");
        stringArray.add("3 2 597.25 924.71875 658.25 897.6719 722.3125 876.6719");
        stringArray.add("3 3 583.25 968.7344 648.25 985.75 715.3125 1002.7656");

        stringArray.add("6 1 224.0625 389.35938 285.125 409.375 356.125 436.42188 414.1875 480.4375 464.1875 528.4375 509.1875 578.5");
        stringArray.add("5 2 203.0625 714.5781 264.0625 697.5625 332.125 673.5625 397.125 653.5469 471.1875 633.53125");
        stringArray.add("5 3 210.0625 738.5781 275.125 721.5625 342.125 697.5625 403.125 680.5469 471.1875 660.53125");
        stringArray.add("3 4 468.1875 809.6406 509.1875 748.6094 529.1875 690.5781");

        stringArray.add("3 1 271.0625 175.23438 336.125 165.25 414.1875 165.25 ");
        stringArray.add("4 2 271.0625 284.3125 339.125 284.3125 407.125 257.3125 451.1875 206.26562");
        stringArray.add("6 3 268.0625 321.34375 336.125 340.375 403.125 351.34375 471.1875 365.35938 542.1875 379.375 610.25 395.35938");
        stringArray.add("4 4 515.1875 202.28125 559.25 260.3125 607.25 307.32812 654.25 355.375");

        stringArray.add("6 3 529.1875 141.20312 600.25 118.1875 669.00995 104.21875 749.3125 111.203125 817.3125 121.1875 878.375 141.20312");
        stringArray.add("4 4 726.3125 348.34375 770.3125 301.32812 820.375 257.3125 868.375 213.25");

        stringArray.add("3 1 570.25 561.4844 603.25 507.4375 644.25 450.39062");
        stringArray.add("3 2 597.25 619.5156 661.25 629.5 726.3125 646.5156");
        stringArray.add("3 3 587.25 643.5156 654.25 660.53125 722.3125 673.5625");
        stringArray.add("4 4 702.3125 453.39062 722.3125 507.4375 753.3125 561.4844 773.3125 622.5156");
        stringArray.add("2 5 780.3125 738.5781 780.3125 795.625");
        stringArray.add("2 1 770.3125 897.6719 773.3125 961.75");
        stringArray.add("6 2 739.3125 399.39062 803.3125 392.35938 875.375 395.35938 953.375 392.35938 1020.4375 392.35938 1095.5 392.35938");
        stringArray.add("5 3 749.3125 429.39062 817.3125 456.4375 878.375 484.42188 946.375 511.46875 1010.4375 534.4844");
        stringArray.add("4 4 831.375 646.5156 892.375 612.53125 956.4375 589.5156 1031.4375 572.5");
        stringArray.add("4 5 858.375 687.5781 936.375 687.5781 1000.4375 673.5625 1065.4375 660.53125");
        stringArray.add("4 1 858.375 710.59375 929.375 710.59375 1004.4375 704.5469 1068.4375 687.5781");
        stringArray.add("4 2 827.375 738.5781 885.375 778.6094 953.375 795.625 1020.4375 802.6094");
        stringArray.add("3 3 834.375 849.6719 905.375 839.6406 970.4375 836.6406");
        stringArray.add("5 4 820.375 998.7344 881.375 975.71875 946.375 948.71875 1000.4375 903.6719 1054.4375 853.65625");
        stringArray.add("4 5 861.375 1015.75 929.375 1008.7656 993.4375 1002.7656 1065.4375 992.7344");
        stringArray.add("6 1 814.3125 1049.7812 881.375 1076.7812 943.375 1090.7969 1014.4375 1097.8281 1095.5 1097.8281 1163.5 1080.8125");

        stringArray.add("4 1 956.4375 213.25 1010.4375 257.3125 1065.4375 307.32812 1109.5 345.34375");
        stringArray.add("2 2 1115.5 443.40625 1095.5 497.45312");
        stringArray.add("2 3 1139.5 453.39062 1119.5 507.4375");
        stringArray.add("1 4 1102.5 612.53125");
        stringArray.add("1 5 1126.5 595.5156");
        stringArray.add("2 1 1115.5 700.5625 1095.5 765.625");
        stringArray.add("2 2 1139.5 714.5781 1122.5 761.59375");
        stringArray.add("2 3 1105.5 866.6875 1109.5 937.7031");
        stringArray.add("2 4 1129.5 870.6719 1132.5 920.6875");
        stringArray.add("1 5 1159.5 1015.75");
        stringArray.add("1 1 1173.5 998.7344");

        stringArray.add("6 1 993.4375 179.26562 1068.4375 192.25 1143.5 206.26562 1207.5 219.25 1275.5625 233.26562 1346.5625 243.29688");
        stringArray.add("3 2 1217.5 341.35938 1278.5625 321.34375 1346.5625 297.29688");
        stringArray.add("6 3 1204.5 375.34375 1268.5625 368.35938 1349.5625 358.375 1421.625 348.34375 1482.625 334.32812 1560.6875 324.34375");
        stringArray.add("3 4 1197.5 426.39062 1261.5625 450.39062 1322.5625 467.40625");
        stringArray.add("4 5 1139.5 534.4844 1193.5 502.9375 1261.5625 487.42188 1332.5625 497.45312");
        stringArray.add("2 1 1180.5 636.53125 1251.5625 636.53125");
        stringArray.add("2 2 1305.5625 602.5 1349.5625 548.4531");
        stringArray.add("2 3 1180.5 660.53125 1251.5625 660.53125");
        stringArray.add("2 4 1326.5625 612.53125 1366.625 565.46875");
        stringArray.add("2 5 1149.5 812.6406 1204.5 805.6094");
        stringArray.add("2 1 1282.5625 761.59375 1299.5625 714.5781");
        stringArray.add("2 2 1176.5 910.7031 1221.5 863.6875");
        stringArray.add("2 3 1261.5625 1039.7969 1332.5625 1029.7656");
        stringArray.add("3 4 1299.5625 870.6719 1336.5625 931.7031 1363.8694 978.71875");

        stringArray.add("5 1 1448.625 233.26562 1509.6875 196.23438 1573.6875 162.25 1638.75 145.23438 1712.75 135.20312");
        stringArray.add("2 2 1468.625 274.28125 1532.6875 284.3125");
        stringArray.add("4 3 1404.625 446.40625 1461.625 402.39062 1519.6875 379.375 1587.6875 351.34375");
        stringArray.add("3 4 1444.625 467.40625 1509.6875 453.39062 1583.6875 446.40625");
        stringArray.add("3 5 1444.625 497.45312 1515.6875 484.42188 1590.6875 477.4375");
        stringArray.add("5 1 1360.5625 646.5156 1427.625 605.5 1482.625 575.5 1543.6875 544.46875 1607.6875 514.46875");
        stringArray.add("2 2 1360.5625 700.5625 1407.625 710.59375");
        stringArray.add("3 3 1329.5625 819.625 1393.625 805.6094 1454.625 768.625");
        stringArray.add("4 4 1414.625 975.71875 1441.625 920.6875 1488.625 870.6719 1539.6875 819.625");
        stringArray.add("4 5 1438.625 988.75 1475.625 927.71875 1505.6875 887.6875 1563.6875 843.6719");
        stringArray.add("6 1 1468.625 1012.75 1536.6875 992.7344 1600.6875 978.71875 1678.75 1000.2344 1739.75 1041.2969 1794.8125 1083.8125");

        stringArray.add("3 1 1627.6875 260.3125 1675.75 209.26562 1736.75 169.23438");
        stringArray.add("3 2 1780.8125 199.23438 1794.8125 267.29688 1800.8125 334.32812");
        stringArray.add("3 3 1532.6875 690.5781 1593.6875 670.5625 1668.75 663.53125");

        stringArray.add("2 1 1844.8125 175.23438 1888.8125 209.26562");
        stringArray.add("2 2 1821.8125 189.25 1875.8125 233.26562");
        stringArray.add("2 3 1892.8125 297.29688 1861.8125 338.35938");
        stringArray.add("2 4 1912.875 311.3125 1878.8125 358.375");
        stringArray.add("2 5 1766.75 395.35938 1699.75 426.39062");
        stringArray.add("2 1 1641.75 365.35938 1641.75 416.40625");
        stringArray.add("2 2 1773.75 423.39062 1719.75 453.39062");
        stringArray.add("2 3 1821.8125 446.40625 1824.8125 514.46875");
        stringArray.add("2 4 1848.8125 446.40625 1855.8125 511.46875");
        stringArray.add("2 5 1719.75 507.4375 1780.8125 538.46875");
        stringArray.add("2 1 1678.75 551.4531 1692.75 605.5");
        stringArray.add("2 2 1794.8125 592.5156 1749.75 639.53125");
        stringArray.add("2 3 1821.8125 612.53125 1770.75 660.53125");
        stringArray.add("2 4 1770.75 714.5781 1804.8125 754.6094");
        stringArray.add("2 5 1695.75 727.5625 1648.75 765.625");
        stringArray.add("2 1 1682.75 707.59375 1624.6875 748.6094");
        stringArray.add("2 2 1661.75 809.6406 1719.75 812.6406");

        stringArray.add("1 1 1539.6875 754.6094");
        stringArray.add("4 2 1482.625 683.5469 1526.6875 629.5 1580.6875 592.5156 1631.6875 541.46875");
        stringArray.add("4 3 1783.8125 849.6719 1790.8125 917.6875 1807.8125 981.71875 1834.8125 1046.7812");
        stringArray.add("5 4 1621.6875 853.65625 1668.75 903.6719 1712.75 951.71875 1756.75 995.7344 1804.8125 1053.7656");



//        stringArray.add("1 3 193.0625 243.29688");

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
