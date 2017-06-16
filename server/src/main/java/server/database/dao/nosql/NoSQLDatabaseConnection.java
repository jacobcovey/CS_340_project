package server.database.dao.nosql;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Riley on 6/16/2017.
 */

public class NoSQLDatabaseConnection {

    public static String serializeObject(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}
