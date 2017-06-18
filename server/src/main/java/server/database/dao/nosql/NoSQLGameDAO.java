package server.database.dao.nosql;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import server.database.dao.iGameDAO;
import server.model.Game;

public class NoSQLGameDAO extends JSONDatabase implements iGameDAO {

    public NoSQLGameDAO() {
        super("database/games.json");
    }

    @Override
    public boolean create(Game game) {
        List<Game> games = read();
        games.add(game);
        return writeToDb(gson.toJson(games));
    }


    @Override
    public List<Game> read() {
        String gamesString;
        try {
            gamesString = readFromDb();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Game[] games = gson.fromJson(gamesString, Game[].class);
        return new ArrayList<>(Arrays.asList(games));
    }

    @Override
    public boolean clear() {
        return writeToDb("[]");
    }

    @Override
    public boolean delete(String id) {
        List<Game> games = read();
        List<Game> filteredGames = new ArrayList<>();
        for (Game g : games) {
            if (!g.getId().equals(id)) {
                filteredGames.add(g);
            }
        }
        return writeToDb(gson.toJson(filteredGames));
    }

    @Override
    public boolean update() {
        return true;
    }

}
