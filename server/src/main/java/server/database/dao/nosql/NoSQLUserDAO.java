package server.database.dao.nosql;

import com.google.gson.Gson;

import server.database.dao.iUserDAO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import shared.classes.User;

import static server.database.dao.nosql.NoSQLDatabaseConnection.serializeObject;

/**
 * Created by Dylan on 6/15/2017.
 */

public class NoSQLUserDAO implements iUserDAO {
    @Override
    public boolean create(User user) {
        if (user == null) {
            File yourFile = new File("database/users.json");
//        FileWriter fw = null;
//        BufferedWriter bw = null;
            try {
                yourFile.createNewFile(); // if file already exists will do nothing
//            fw = new FileWriter(yourFile);
//            bw = new BufferedWriter(fw);
//            bw.write(" " + serializeObject(userData));
            } catch (IOException e) {
                System.out.println("Failed to create non sql users.json database");
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
        } else {
            File yourFile = new File("database/users.json");
            FileWriter fw = null;
            BufferedWriter bw = null;
            try {
                yourFile.createNewFile(); // if file already exists will do nothing
            fw = new FileWriter(yourFile);
            bw = new BufferedWriter(fw);
            bw.write(" " + serializeObject(user));
            } catch (IOException e) {
                System.out.println("Failed to create non sql users.json database");
                return false;
            } finally {
                try {

                    if (bw != null)
                        bw.close();

                    if (fw != null)
                        fw.close();

                } catch (IOException ex) {

                    ex.printStackTrace();

                }
            }

            return true;
        }

    }

    @Override
    public Set<User> read() {
        if (create(null)) {

        }
        String filePath = "database/users.json";

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

        return turnStringToSetOfUsers(sb.toString());
    }

    private Set<User> turnStringToSetOfUsers(String s) {
        Set<User> output = new HashSet<>();
        String[] userStrings = s.split(" ");
        for (String str : userStrings) {
            if (str.length() > 0) {
                output.add(turnStringToUser(str));
            }
        }
        return output;
    }

    private User turnStringToUser(String str) {
        Gson gson = new Gson();
        return gson.fromJson(str, User.class);
    }

    @Override
    public boolean update() {
        return true;
    }

    @Override
    public boolean delete() {
        File file = new File("database/users.json");
        file.delete();
        return true;
    }
}
