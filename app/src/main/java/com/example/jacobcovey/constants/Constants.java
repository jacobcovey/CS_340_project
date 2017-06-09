package com.example.jacobcovey.constants;

import com.example.jacobcovey.game_board.Route;

import java.util.ArrayList;
import java.util.List;

import static shared.classes.City.ATLANTA;
import static shared.classes.City.BOSTON;
import static shared.classes.City.CALGARY;
import static shared.classes.City.CHARLESTON;
import static shared.classes.City.CHICAGO;
import static shared.classes.City.DALLAS;
import static shared.classes.City.DENVER;
import static shared.classes.City.DULUTH;
import static shared.classes.City.EL_PASO;
import static shared.classes.City.HELENA;
import static shared.classes.City.HOUSTON;
import static shared.classes.City.KANSAS_CITY;
import static shared.classes.City.LAS_VEGAS;
import static shared.classes.City.LITTLE_ROCK;
import static shared.classes.City.LOS_ANGELES;
import static shared.classes.City.MIAMI;
import static shared.classes.City.MONTREAL;
import static shared.classes.City.NASHVILLE;
import static shared.classes.City.NEW_ORLEANS;
import static shared.classes.City.NEW_YORK;
import static shared.classes.City.OKLAHOMA_CITY;
import static shared.classes.City.OMAHA;
import static shared.classes.City.PHOENIX;
import static shared.classes.City.PITTSBURG;
import static shared.classes.City.PORTLAND;
import static shared.classes.City.RALEIGH;
import static shared.classes.City.SALT_LAKE_CITY;
import static shared.classes.City.SANTA_FE;
import static shared.classes.City.SAN_FRANCISCO;
import static shared.classes.City.SAULT_ST_MARIE;
import static shared.classes.City.SEATTLE;
import static shared.classes.City.ST_LOUIS;
import static shared.classes.City.TORONTO;
import static shared.classes.City.VANCOUVER;
import static shared.classes.City.WASHINGTON;
import static shared.classes.City.WINNIPEG;
import static shared.classes.TrainCardColors.BLACK;
import static shared.classes.TrainCardColors.BLUE;
import static shared.classes.TrainCardColors.GREEN;
import static shared.classes.TrainCardColors.ORANGE;
import static shared.classes.TrainCardColors.PURPLE;
import static shared.classes.TrainCardColors.RED;
import static shared.classes.TrainCardColors.WHITE;
import static shared.classes.TrainCardColors.WILD;
import static shared.classes.TrainCardColors.YELLOW;

/**
 * Created by billrichards on 6/5/17.
 */

public class Constants {
    public static final String FIRST_TURN = "FirstTurn";
    public static final String YOUR_TURN = "YourTurn";
    public static final String DESTINATION_CARDS_DRAWN = "DestinationCardsDrawn";
    public static final String ONE_TRAIN_CARD_SELECTED = "OneTrainCardSelected";
    public static final String CLAIMING_ROUTE = "ClaimingRoute";
    public static final String NOT_YOUR_TURN = "NotYourTurn";
    public static final List<Route> ROUTES = initializeRoutes();

    private static List<Route> initializeRoutes() {
        List<Route> routeArray = new ArrayList<>();

        routeArray.add(new Route(1, 1, WILD, SEATTLE, VANCOUVER, "193.0625 243.29688"));
        routeArray.add(new Route(1, 2, WILD, SEATTLE, VANCOUVER, "220.0625 243.29688"));
        routeArray.add(new Route(1, 3, WILD, SEATTLE, PORTLAND, "163.0625 328.32812"));
        routeArray.add(new Route(1, 4, WILD, SEATTLE, PORTLAND, "197.0625 345.34375"));
        routeArray.add(new Route(5, 5, GREEN, PORTLAND, SAN_FRANCISCO, "132.0 426.39062 105.0 494.45312 98.0 561.4844 102.0 622.5156 115.0 700.5625"));
        routeArray.add(new Route(5, 6, PURPLE, PORTLAND, SAN_FRANCISCO, "159.0625 433.42188 136.0 490.42188 125.0 558.4844 132.0 626.5 139.0625 690.5781"));
        routeArray.add(new Route(3, 7, YELLOW, SAN_FRANCISCO, LOS_ANGELES, "153.0625 802.6094 183.0625 859.65625 231.0625 914.6875"));
        routeArray.add(new Route(3, 8, PURPLE, SAN_FRANCISCO, LOS_ANGELES, "173.0625 792.625 207.0625 846.6719 254.0625 903.6719"));
        routeArray.add(new Route(2, 9, WILD, LOS_ANGELES, LAS_VEGAS, "305.125 880.65625 366.125 836.6406"));
        routeArray.add(new Route(3, 10, WILD, LOS_ANGELES, PHOENIX, "346.125 920.6875 407.125 917.6875 481.1875 924.71875"));
        routeArray.add(new Route(6, 11, BLACK, LOS_ANGELES, EL_PASO, "342.125 975.71875 407.125 1012.75 464.1875 1029.7656 542.1875 1046.7812 610.25 1046.7812 681.25 1032.7656"));
        routeArray.add(new Route(5, 12, WHITE, PHOENIX, DENVER, "556.25 897.6719 583.25 846.6719 620.25 788.6406 664.25 744.5781 736.3125 707.59375"));
        routeArray.add(new Route(3, 13, WILD, PHOENIX, SANTA_FE, "597.25 924.71875 658.25 897.6719 722.3125 876.6719"));
        routeArray.add(new Route(3, 14, WILD, PHOENIX, EL_PASO, "583.25 968.7344 648.25 985.75 715.3125 1002.7656"));
        routeArray.add(new Route(6, 15, BLUE, PORTLAND, SALT_LAKE_CITY, "224.0625 389.35938 285.125 409.375 356.125 436.42188 414.1875 480.4375 464.1875 528.4375 509.1875 578.5"));
        routeArray.add(new Route(5, 16, ORANGE, SAN_FRANCISCO, SALT_LAKE_CITY, "203.0625 714.5781 264.0625 697.5625 332.125 673.5625 397.125 653.5469 471.1875 633.53125"));
        routeArray.add(new Route(5, 17, WHITE, SAN_FRANCISCO, SALT_LAKE_CITY, "210.0625 738.5781 275.125 721.5625 342.125 697.5625 403.125 680.5469 471.1875 660.53125"));
        routeArray.add(new Route(3, 18, ORANGE, LAS_VEGAS, SALT_LAKE_CITY, "468.1875 809.6406 509.1875 748.6094 529.1875 690.5781"));
        routeArray.add(new Route(3, 19, WILD, VANCOUVER, CALGARY, "271.0625 175.23438 336.125 165.25 414.1875 165.25 "));
        routeArray.add(new Route(4, 20, WILD, SEATTLE, CALGARY, "271.0625 284.3125 339.125 284.3125 407.125 257.3125 451.1875 206.26562"));
        routeArray.add(new Route(6, 21, YELLOW, SEATTLE, HELENA, "268.0625 321.34375 336.125 340.375 403.125 351.34375 471.1875 365.35938 542.1875 379.375 610.25 395.35938"));
        routeArray.add(new Route(4, 22, WILD, CALGARY, HELENA, "515.1875 202.28125 559.25 260.3125 607.25 307.32812 654.25 355.375"));
        routeArray.add(new Route(6, 23, WHITE, CALGARY, WINNIPEG, "529.1875 141.20312 600.25 118.1875 669.00995 104.21875 749.3125 111.203125 817.3125 121.1875 878.375 141.20312"));
        routeArray.add(new Route(4, 24, BLUE, HELENA, WINNIPEG, "726.3125 348.34375 770.3125 301.32812 820.375 257.3125 868.375 213.25"));
        routeArray.add(new Route(3, 25, PURPLE, SALT_LAKE_CITY, HELENA, "570.25 561.4844 603.25 507.4375 644.25 450.39062"));
        routeArray.add(new Route(3, 26, RED, SALT_LAKE_CITY, DENVER, "597.25 619.5156 661.25 629.5 726.3125 646.5156"));
        routeArray.add(new Route(3, 27, YELLOW, SALT_LAKE_CITY, DENVER, "587.25 643.5156 654.25 660.53125 722.3125 673.5625"));
        routeArray.add(new Route(4, 28, GREEN, HELENA, DENVER, "702.3125 453.39062 722.3125 507.4375 753.3125 561.4844 773.3125 622.5156"));
        routeArray.add(new Route(2, 29, WILD, DENVER, SANTA_FE, "780.3125 738.5781 780.3125 795.625"));
        routeArray.add(new Route(2, 30, WILD, SANTA_FE, EL_PASO, "770.3125 897.6719 773.3125 961.75"));
        routeArray.add(new Route(6, 31, ORANGE, HELENA, DULUTH, "739.3125 399.39062 803.3125 392.35938 875.375 395.35938 953.375 392.35938 1020.4375 392.35938 1095.5 392.35938"));
        routeArray.add(new Route(5, 32, RED, HELENA, OMAHA, "749.3125 429.39062 817.3125 456.4375 878.375 484.42188 946.375 511.46875 1010.4375 534.4844"));
        routeArray.add(new Route(4, 33, PURPLE, DENVER, OMAHA, "831.375 646.5156 892.375 612.53125 956.4375 589.5156 1031.4375 572.5"));
        routeArray.add(new Route(4, 34, BLACK, DENVER, KANSAS_CITY, "858.375 687.5781 936.375 687.5781 1000.4375 673.5625 1065.4375 660.53125"));
        routeArray.add(new Route(4, 35, ORANGE, DENVER, KANSAS_CITY, "858.375 710.59375 929.375 710.59375 1004.4375 704.5469 1068.4375 687.5781"));
        routeArray.add(new Route(4, 36, RED, DENVER, OKLAHOMA_CITY, "827.375 738.5781 885.375 778.6094 953.375 795.625 1020.4375 802.6094"));
        routeArray.add(new Route(3, 37, BLUE, SANTA_FE, OKLAHOMA_CITY, "834.375 849.6719 905.375 839.6406 970.4375 836.6406"));
        routeArray.add(new Route(5, 38, YELLOW, EL_PASO, OKLAHOMA_CITY, "820.375 998.7344 881.375 975.71875 946.375 948.71875 1000.4375 903.6719 1054.4375 853.65625"));
        routeArray.add(new Route(4, 39, RED, EL_PASO, DALLAS, "861.375 1015.75 929.375 1008.7656 993.4375 1002.7656 1065.4375 992.7344"));
        routeArray.add(new Route(6, 40, GREEN, EL_PASO, HOUSTON, "814.3125 1049.7812 881.375 1076.7812 943.375 1090.7969 1014.4375 1097.8281 1095.5 1097.8281 1163.5 1080.8125"));
        routeArray.add(new Route(4, 41, BLACK, WINNIPEG, DULUTH, "956.4375 213.25 1010.4375 257.3125 1065.4375 307.32812 1109.5 345.34375"));
        routeArray.add(new Route(2, 42, WILD, DULUTH, OMAHA, "1115.5 443.40625 1095.5 497.45312"));
        routeArray.add(new Route(2, 43, WILD, DULUTH, OMAHA, "1139.5 453.39062 1119.5 507.4375"));
        routeArray.add(new Route(1, 44, WILD, OMAHA, KANSAS_CITY, "1102.5 612.53125"));
        routeArray.add(new Route(1, 45, WILD, OMAHA, KANSAS_CITY, "1126.5 595.5156"));
        routeArray.add(new Route(2, 46, WILD, KANSAS_CITY, OKLAHOMA_CITY, "1115.5 700.5625 1095.5 765.625"));
        routeArray.add(new Route(2, 47, WILD, KANSAS_CITY, OKLAHOMA_CITY, "1139.5 714.5781 1122.5 761.59375"));
        routeArray.add(new Route(2, 48, WILD, OKLAHOMA_CITY, DALLAS, "1105.5 866.6875 1109.5 937.7031"));
        routeArray.add(new Route(2, 49, WILD, OKLAHOMA_CITY, DALLAS, "1129.5 870.6719 1132.5 920.6875"));
        routeArray.add(new Route(1, 50, WILD, DALLAS, HOUSTON, "1159.5 1015.75"));
        routeArray.add(new Route(1, 51, WILD, DALLAS, HOUSTON, "1173.5 998.7344"));
        routeArray.add(new Route(6, 52, WILD, WINNIPEG, SAULT_ST_MARIE, "993.4375 179.26562 1068.4375 192.25 1143.5 206.26562 1207.5 219.25 1275.5625 233.26562 1346.5625 243.29688"));
        routeArray.add(new Route(3, 53, WILD, DULUTH, SAULT_ST_MARIE, "1217.5 341.35938 1278.5625 321.34375 1346.5625 297.29688"));
        routeArray.add(new Route(6, 54, PURPLE, DULUTH, TORONTO, "1204.5 375.34375 1268.5625 368.35938 1349.5625 358.375 1421.625 348.34375 1482.625 334.32812 1560.6875 324.34375"));
        routeArray.add(new Route(3, 55, RED, DULUTH, CHICAGO, "1197.5 426.39062 1261.5625 450.39062 1322.5625 467.40625"));
        routeArray.add(new Route(4, 56, BLUE, OMAHA, CHICAGO, "1139.5 534.4844 1193.5 502.9375 1261.5625 487.42188 1332.5625 497.45312"));
        routeArray.add(new Route(2, 57, BLUE, KANSAS_CITY, ST_LOUIS, "1180.5 636.53125 1251.5625 636.53125"));
        routeArray.add(new Route(2, 58, GREEN, ST_LOUIS, CHICAGO, "1305.5625 602.5 1349.5625 548.4531"));
        routeArray.add(new Route(2, 59, PURPLE, KANSAS_CITY, ST_LOUIS, "1180.5 660.53125 1251.5625 660.53125"));
        routeArray.add(new Route(2, 60, WHITE, ST_LOUIS, CHICAGO, "1326.5625 612.53125 1366.625 565.46875"));
        routeArray.add(new Route(2, 61, WILD, OKLAHOMA_CITY, LITTLE_ROCK, "1149.5 812.6406 1204.5 805.6094"));
        routeArray.add(new Route(2, 62, WILD, LITTLE_ROCK, ST_LOUIS, "1282.5625 761.59375 1299.5625 714.5781"));
        routeArray.add(new Route(2, 63, WILD, DALLAS, LITTLE_ROCK, "1176.5 910.7031 1221.5 863.6875"));
        routeArray.add(new Route(2, 64, WILD, HOUSTON, NEW_ORLEANS, "1261.5625 1039.7969 1332.5625 1029.7656"));
        routeArray.add(new Route(3, 65, GREEN, LITTLE_ROCK, NEW_ORLEANS, "1299.5625 870.6719 1336.5625 931.7031 1363.8694 978.71875"));
        routeArray.add(new Route(5, 66, BLACK, SAULT_ST_MARIE, MONTREAL, "1448.625 233.26562 1509.6875 196.23438 1573.6875 162.25 1638.75 145.23438 1712.75 135.20312"));
        routeArray.add(new Route(2, 67, WILD, SAULT_ST_MARIE, TORONTO, "1468.625 274.28125 1532.6875 284.3125"));
        routeArray.add(new Route(4, 68, WHITE, CHICAGO, TORONTO, "1404.625 446.40625 1461.625 402.39062 1519.6875 379.375 1587.6875 351.34375"));
        routeArray.add(new Route(3, 69, ORANGE, CHICAGO, PITTSBURG, "1444.625 467.40625 1509.6875 453.39062 1583.6875 446.40625"));
        routeArray.add(new Route(3, 70, BLACK, CHICAGO, PITTSBURG, "1444.625 497.45312 1515.6875 484.42188 1590.6875 477.4375"));
        routeArray.add(new Route(5, 71, GREEN, ST_LOUIS, PITTSBURG, "1360.5625 646.5156 1427.625 605.5 1482.625 575.5 1543.6875 544.46875 1607.6875 514.46875"));
        routeArray.add(new Route(2, 72, WILD, ST_LOUIS, NASHVILLE, "1360.5625 700.5625 1407.625 710.59375"));
        routeArray.add(new Route(3, 73, WHITE, LITTLE_ROCK, NASHVILLE, "1329.5625 819.625 1393.625 805.6094 1454.625 768.625"));
        routeArray.add(new Route(4, 74, YELLOW, NEW_ORLEANS, ATLANTA, "1414.625 975.71875 1441.625 920.6875 1488.625 870.6719 1539.6875 819.625"));
        routeArray.add(new Route(4, 75, ORANGE, NEW_ORLEANS, ATLANTA, "1438.625 988.75 1475.625 927.71875 1505.6875 887.6875 1563.6875 843.6719"));
        routeArray.add(new Route(6, 76, RED, NEW_ORLEANS, MIAMI, "1468.625 1012.75 1536.6875 992.7344 1600.6875 978.71875 1678.75 1000.2344 1739.75 1041.2969 1794.8125 1083.8125"));
        routeArray.add(new Route(3, 77, WILD, TORONTO, MONTREAL, "1627.6875 260.3125 1675.75 209.26562 1736.75 169.23438"));
        routeArray.add(new Route(3, 78, BLUE, MONTREAL, NEW_YORK, "1780.8125 199.23438 1794.8125 267.29688 1800.8125 334.32812"));
        routeArray.add(new Route(3, 79, BLACK, NASHVILLE, RALEIGH, "1532.6875 690.5781 1593.6875 670.5625 1668.75 663.53125"));
        routeArray.add(new Route(2, 80, WILD, MONTREAL, BOSTON, "1844.8125 175.23438 1888.8125 209.26562"));
        routeArray.add(new Route(2, 81, WILD, MONTREAL, BOSTON, "1821.8125 189.25 1875.8125 233.26562"));
        routeArray.add(new Route(2, 82, YELLOW, BOSTON, NEW_YORK, "1892.8125 297.29688 1861.8125 338.35938"));
        routeArray.add(new Route(2, 83, RED, BOSTON, NEW_YORK, "1912.875 311.3125 1878.8125 358.375"));
        routeArray.add(new Route(2, 84, WHITE, NEW_YORK, PITTSBURG, "1766.75 395.35938 1699.75 426.39062"));
        routeArray.add(new Route(2, 85, WILD, TORONTO, PITTSBURG, "1641.75 365.35938 1641.75 416.40625"));
        routeArray.add(new Route(2, 86, GREEN, NEW_YORK, PITTSBURG, "1773.75 423.39062 1719.75 453.39062"));
        routeArray.add(new Route(2, 87, ORANGE, NEW_YORK, WASHINGTON, "1821.8125 446.40625 1824.8125 514.46875"));
        routeArray.add(new Route(2, 88, BLACK, NEW_YORK, WASHINGTON, "1848.8125 446.40625 1855.8125 511.46875"));
        routeArray.add(new Route(2, 89, WILD, PITTSBURG, WASHINGTON, "1719.75 507.4375 1780.8125 538.46875"));
        routeArray.add(new Route(2, 90, WILD, PITTSBURG, RALEIGH, "1678.75 551.4531 1692.75 605.5"));
        routeArray.add(new Route(2, 91, WILD, WASHINGTON, RALEIGH, "1794.8125 592.5156 1749.75 639.53125"));
        routeArray.add(new Route(2, 92, WILD, WASHINGTON, RALEIGH, "1821.8125 612.53125 1770.75 660.53125"));
        routeArray.add(new Route(2, 93, WILD, RALEIGH, CHARLESTON, "1770.75 714.5781 1804.8125 754.6094"));
        routeArray.add(new Route(2, 94, WILD, RALEIGH, ATLANTA, "1695.75 727.5625 1648.75 765.625"));
        routeArray.add(new Route(2, 95, WILD, RALEIGH, ATLANTA, "1682.75 707.59375 1624.6875 748.6094"));
        routeArray.add(new Route(2, 96, WILD, ATLANTA, CHARLESTON, "1661.75 809.6406 1719.75 812.6406"));
        routeArray.add(new Route(1, 97, WILD, ATLANTA, NASHVILLE, "1539.6875 754.6094"));
        routeArray.add(new Route(4, 98, YELLOW, NASHVILLE, PITTSBURG, "1482.625 683.5469 1526.6875 629.5 1580.6875 592.5156 1631.6875 541.46875"));
        routeArray.add(new Route(4, 99, PURPLE, CHARLESTON, MIAMI, "1783.8125 849.6719 1790.8125 917.6875 1807.8125 981.71875 1834.8125 1046.7812"));
        routeArray.add(new Route(5, 100, BLUE, ATLANTA, MIAMI, "1621.6875 853.65625 1668.75 903.6719 1712.75 951.71875 1756.75 995.7344 1804.8125 1053.7656"));

        return routeArray;
    }

}



