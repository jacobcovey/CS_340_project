package server.database.dao.nosql;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import server.database.dao.iCommandDAO;
import shared.classes.CommandData;


public class NoSQLCommandDAO extends JSONDatabase implements iCommandDAO {

    public NoSQLCommandDAO() {
        super("database/commands.json");
    }

    @Override
    public boolean create(CommandData commandData) {
        List<CommandData> commands = read();
        commands.add(commandData);
        return writeToDb(gson.toJson(commands));
    }

    @Override
    public List<CommandData> read() {
        String commandsString;
        try {
            commandsString = readFromDb();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        CommandData[] commands = gson.fromJson(commandsString, CommandData[].class);
        return new ArrayList<>(Arrays.asList(commands));
    }

    @Override
    public boolean delete(String id) {
        // NOTE: id is a gameId
        List<CommandData> commands = read();
        List<CommandData> filteredCommands = new ArrayList<>();
        for (CommandData c : commands) {
            if (!c.getGameId().equals(id)) {
                filteredCommands.add(c);
            }
        }
        return writeToDb(gson.toJson(filteredCommands));
    }

    @Override
    public boolean clear() {
        return writeToDb("[]");
    }

    @Override
    public boolean update() {
        return true;
    }

}
