package server.database.dao.nosql;

import com.google.gson.Gson;

import server.database.dao.iCommandDAO;
import shared.classes.CommandData;
import server.database.dao.iCommandDAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static server.database.dao.nosql.NoSQLDatabaseConnection.serializeObject;


/**
 * Created by Dylan on 6/15/2017.
 */

public class NoSQLCommandDAO implements iCommandDAO {

    @Override
    public boolean create(CommandData commandData) {
        if (commandData == null) {
            File yourFile = new File("database/commands.json");
            try {
                yourFile.createNewFile(); // if file already exists will do nothing
            } catch (IOException e) {
                System.out.println("Failed to create non sql commands.json database");
                return false;
            }

            return true;
        } else {
            File yourFile = new File("database/commands.json");
            FileWriter fw = null;
            BufferedWriter bw = null;
            List<CommandData> commands = new ArrayList<>();
            commands = read(commandData.getGameId());
            try {
                yourFile.createNewFile(); // if file already exists will do nothing
                fw = new FileWriter(yourFile);
                bw = new BufferedWriter(fw);
                commands.add(commandData);
                bw.write(" " + serializeObject(commands));
            } catch (IOException e) {
                System.out.println("Failed to create non sql commands.json database");
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
    public List<CommandData> read(String id) {
        if (create(null)) {

        }
        if (id == null) {
            String filePath = "database/commands.json";

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

            return turnStringToListOfCommands(sb.toString());
        } else {
            String filePath = "database/commands.json";

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

            List<CommandData> allCmds = turnStringToListOfCommands(sb.toString());
            return getCommandsFromListById(allCmds, id);
        }

    }

    private List<CommandData> getCommandsFromListById(List<CommandData> allCmds, String id) {
        List<CommandData> output = new ArrayList<>();
        for (CommandData c : allCmds) {
            if (c.getGameId().equals(id)) {
                output.add(c);
            }
        }
        return output;
    }

    private List<CommandData> turnStringToListOfCommands(String s) {
        List<CommandData> output = new ArrayList<>();
        String[] commandStrings = s.split(" ");
        for (String str : commandStrings) {
            if (str.length() > 0) {
                output.add(turnStringToCommand(str));
            }
        }
        return output;
    }

    private CommandData turnStringToCommand(String str) {
        Gson gson = new Gson();
        return gson.fromJson(str, CommandData.class);
    }

    @Override
    public boolean update() {
        return true;
    }

    @Override
    public boolean delete(String id) {
        if (id == null) {
            File file = new File("database/commands.json");
            file.delete();
        }

        return true;
    }
}
