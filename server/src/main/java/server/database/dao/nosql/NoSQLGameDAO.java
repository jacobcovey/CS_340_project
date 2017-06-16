package server.database.dao.nosql;

import com.google.gson.Gson;

import server.database.dao.iGameDAO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import shared.classes.Game;

import static server.database.dao.nosql.NoSQLDatabaseConnection.serializeObject;

/**
 * Created by Dylan on 6/15/2017.
 */

public class NoSQLGameDAO implements iGameDAO {
    @Override
    public boolean create() {
        File yourFile = new File("database/games.json");
//        FileWriter fw = null;
//        BufferedWriter bw = null;
        try {
            yourFile.createNewFile(); // if file already exists will do nothing
//            fw = new FileWriter(yourFile);
//            bw = new BufferedWriter(fw);
//            bw.write(" " + serializeObject(gameData));
        } catch (IOException e) {
            System.out.println("Failed to create non sql games.json database");
            return false;
        } finally {
//            try {
//
//                if (bw != null)
//                    bw.close();
//
//                if (fw != null)
//                    fw.close();
//
//            } catch (IOException ex) {
//
//                ex.printStackTrace();
//
//            }
        }

        return true;
    }

    @Override
    public List<Game> read() {
        if (create()) {

        }
        String filePath = "database/games.json";

        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine() + "\n");
        }
        scanner.close();

        return turnStringToListOfGames(sb.toString());
    }

    private List<Game> turnStringToListOfGames(String s) {
        List<Game> output = new ArrayList<>();
        String[] gameStrings = s.split(" ");
        for (String str : gameStrings) {
            if (str.length() > 0) {
                output.add(turnStringToGame(str));
            }
        }
        return output;
    }

    private Game turnStringToGame(String str) {
        Gson gson = new Gson();
        return gson.fromJson(str, Game.class);
    }

    @Override
    public boolean update() {
        return true;
    }

    @Override
    public boolean delete() {
        File file = new File("database/games.json");
        file.delete();
        return true;
    }
}
