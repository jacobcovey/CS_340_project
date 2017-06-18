package server.database.dao.nosql;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by spencer on 6/17/17.
 */

public class JSONDatabase {

    private String dbFilePath;
    protected Gson gson;

    public JSONDatabase(String dbFilePath) {
        this.dbFilePath = dbFilePath;
        this.gson = new Gson();
        createEmptyDbMaybe();
    }

    private void createEmptyDbMaybe() {
        File file = new File(dbFilePath);
        if (file.exists()) {
            return;
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try (PrintWriter writer = new PrintWriter(dbFilePath)) {
            writer.write("[]");
        } catch (FileNotFoundException e) {
            e.printStackTrace(); }
    }

    protected boolean writeToDb(String str) {
        createEmptyDbMaybe();
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(dbFilePath, false))) {
            writer.write(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    protected String readFromDb() throws IOException {
        return new String(Files.readAllBytes(Paths.get(dbFilePath)));
    }
}
