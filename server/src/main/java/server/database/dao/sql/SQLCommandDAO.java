package server.database.dao.sql;

import com.google.gson.Gson;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import server.database.dao.iCommandDAO;
import shared.classes.CommandData;

/**
 * Created by Dylan on 6/15/2017.
 */

public class SQLCommandDAO implements iCommandDAO {
    public SQLCommandDAO() {

    }

    public static CommandData getCommandById(String id) {
        if (id == null) {
            System.out.println("yep");
            return null;
        }
        String selectCommandStmt = "SELECT * FROM COMMANDS WHERE COMMANDS.commandId = '" + id + "'";

        CommandData output = null;
        SQLDatabaseConnection db = new SQLDatabaseConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String serializedCommandString = null;

        try {
            db.openConnection();

            preparedStatement = db.mConnection.prepareStatement(selectCommandStmt);
            resultSet = preparedStatement.executeQuery();

            serializedCommandString = resultSet.getString("data");
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

        output = makeSerStringToCommand(serializedCommandString);
        return output;
    }

    private static CommandData makeSerStringToCommand(String serializedGameString) {
        Gson gson = new Gson();
        CommandData output = gson.fromJson(serializedGameString, CommandData.class);
        System.out.println("made command object from string"); // TODO: 6/15/2017 delete this
        return output;
    }

    public static void addCommandToDB(CommandData data, String gameId) {
        String serializedCommand = serializeObject(data);
        String commandAddString = "INSERT INTO COMMANDS " +
                "(data, gameId) VALUES (" +
                "'" + serializedCommand + "', " +
                "'" + gameId + "'" +
                ")";

        SQLDatabaseConnection db = new SQLDatabaseConnection();

        try {
            db.openConnection();
            PreparedStatement addGameStmt = db.mConnection.prepareStatement(commandAddString);
            addGameStmt.execute();
            addGameStmt.close();
            db.closeConnection();

        } catch (SQLException e) {
            System.out.println("failed to add command");
        }
    }

    private static String serializeObject(CommandData data) {
        Gson gson = new Gson();
        String output = gson.toJson(data);
        return output;
    }

//    public static List<CommandData> getCommandsByGameId(String id) {
//        if (id == null) {
//            System.out.println("yep");
//            return null;
//        }
//        String selectCommandStmt = "SELECT * FROM COMMANDS WHERE COMMANDS.gameId = '" + id + "'";
//
//        List<CommandData> output = null;
//        SQLDatabaseConnection db = new SQLDatabaseConnection();
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        List<String> serializedCommandString = new ArrayList<>();
//
//        try {
//            db.openConnection();
//
//            preparedStatement = db.mConnection.prepareStatement(selectCommandStmt);
//            resultSet = preparedStatement.executeQuery();
//
//            serializedCommandString = resultSet.getString("data");
//            resultSet.close();
//
//        } catch (SQLException e) {
//            return null;
//        } finally {
//            try {
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                }
//                db.closeConnection();
//            } catch (SQLException e) {
//                System.out.println("unable to close result set/prep staement");
//            }
//
//        }
//
//        output = makeSerStringToCommandList(serializedCommandString);
//        return output;
//    }


    @Override
    public boolean create(CommandData commandData) {
        addCommandToDB(commandData, commandData.getGameId());
        return false;
    }

    @Override
    public List<CommandData> read(String id) {
        SQLDatabaseConnection db = new SQLDatabaseConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String selectUserStmt = "SELECT data FROM COMMANDS WHERE gameId = '" + id + "'";

        List<CommandData> output = new ArrayList<>();

        try {
            db.openConnection();

            preparedStatement = db.mConnection.prepareStatement(selectUserStmt);
            resultSet = preparedStatement.executeQuery();
            output.addAll(getListOfCommandsFromRS(resultSet));


            resultSet.close();
            return output;

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
    }

    private List<CommandData> getListOfCommandsFromRS(ResultSet resultSet) {
        List<CommandData> output = new ArrayList<>();
        try {
            while (resultSet.next()) {
                output.add(turnGsonToCommand(resultSet.getString("data")));
            }
        } catch (SQLException e) {
            System.out.println("Unable to get command from Result set");
            e.printStackTrace();
        }
        return output;
    }

    private CommandData turnGsonToCommand(String data) {
        Gson gson = new Gson();

        return gson.fromJson(data, CommandData.class);
    }


    @Override
    public boolean update() {
        return true;
    }

    @Override
    public boolean delete(String id) {
        SQLDatabaseConnection db = new SQLDatabaseConnection();
        if (id == null) {
            db.openConnection();
            db.clearCommandsTable();
            db.closeConnection();
        } else {
            deleteCommandsByGameId(id);
        }
        return true;
    }

    private void deleteCommandsByGameId(String id) {
        String deleteSqlString = "DELETE FROM COMMANDS WHERE gameId = '" + id + "'";
        SQLDatabaseConnection db = new SQLDatabaseConnection();

        db.openConnection();

        PreparedStatement ps = null;
        try {
            ps = db.mConnection.prepareStatement(deleteSqlString);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error when deleting from commands");

        }


        db.closeConnection();
    }
}
