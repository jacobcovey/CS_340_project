package server.database.dao.sql;

import com.google.gson.Gson;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.database.dao.iGameDAO;
import shared.classes.Game;

/**
 * Created by Dylan on 6/15/2017.
 */

public class SQLGameDAO implements iGameDAO {
    public SQLGameDAO() {

    }

    public static Game getGameById(String id) {
        if (id == null) {
            System.out.println("yep");
            return null;
        }
        String selectGameStmt = "SELECT * FROM GAMES WHERE GAMES.gameId = '" + id + "'";

        Game output = null;
        SQLDatabaseConnection db = new SQLDatabaseConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String serializedGameString = null;

        try {
            db.openConnection();

            preparedStatement = db.mConnection.prepareStatement(selectGameStmt);
            resultSet = preparedStatement.executeQuery();

            serializedGameString = resultSet.getString("gameInfoObject");
            resultSet.close();

        } catch (SQLException e) {
            return null;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                db.closeConnection();
            } catch (SQLException e) {
                System.out.println("unable to close result set/prep staement");
            }

        }

        output = makeSerStringToGame(serializedGameString);
        return output;
    }

    private static Game makeSerStringToGame(String serializedGameString) {
        Gson gson = new Gson();
        Game output = gson.fromJson(serializedGameString, Game.class);
        System.out.println("made game object from string"); // TODO: 6/15/2017 delete this
        return output;
    }

    public static void addGameToDB(Game game) {
        String serializedGame = serializeObject(game);
        String gameAddString = "INSERT INTO GAMES " +
                "(gameId, gameInfoObject) VALUES (" +
                "'" + game.getId() + "', " +
                "'" + serializedGame + "', " +
                ")";

        SQLDatabaseConnection db = new SQLDatabaseConnection();

        try {
            db.openConnection();
            PreparedStatement addGameStmt = db.mConnection.prepareStatement(gameAddString);
            addGameStmt.execute();
            addGameStmt.close();
            db.closeConnection();

        } catch (SQLException e) {
            System.out.println("failed to add user");
        }
    }

    private static String serializeObject(Game game) {
        Gson gson = new Gson();
        String output = gson.toJson(game);
        return output;
    }

    @Override
    public boolean create() {
        return true;
    }

    @Override
    public Game read() {
        return null;
    }

    @Override
    public boolean update() {
        return true;
    }

    @Override
    public boolean delete() {
        return true;
    }

}
