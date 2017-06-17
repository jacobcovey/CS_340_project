package server.database.dao.sql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Riley on 6/15/2017.
 */

public class SQLDatabaseConnection {
    public Connection mConnection = null;

    public SQLDatabaseConnection() {
        try {
            Class.forName("org.sqlite.JDBC");

        } catch (ClassNotFoundException e) {
            System.out.println("unable to load class driver");
            e.printStackTrace();
        }
    }

    public void openConnection() {
        try {
            String dbPath = "database" + File.separator + "database.sqlite";
            String connectionURL = "jdbc:sqlite:" + dbPath;

            mConnection = null;
            mConnection = DriverManager.getConnection(connectionURL);
            createIfDoesntExist();
            mConnection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (mConnection != null) {
                mConnection.commit();
            }
        } catch (SQLException e) {
            System.out.println("connection could not be committed");
        }
        try {
            if (mConnection != null) {
                mConnection.close();
            }
        } catch (SQLException e) {
            System.out.println("connection could not be closed");
        }

        mConnection = null;
    }

    public void cancelConnection() {
        try {
            mConnection.rollback();
            mConnection.close();
        } catch (SQLException e) {
            System.out.println("connection could not be closed");
            e.printStackTrace();
        }
        mConnection = null;
    }

    private void createIfDoesntExist() throws SQLException {
        String create_users_table = "CREATE TABLE IF NOT EXISTS USERS"+
                "("+
                "userName varchar(64) primary key,"+
                "password varchar(64)"+
                ");";

        String create_games_table = "CREATE TABLE IF NOT EXISTS GAMES"+
                "("+
                "gameId varchar(64),"+
                "gameInfoObject TEXT"+
                ");";


        String create_commands_table = "CREATE TABLE IF NOT EXISTS COMMANDS"+
                "("+
                "commandId integer primary key autoincrement,"+
                "data text,"+
                "gameId text"+
                ");";

        PreparedStatement usersStmt = this.mConnection.prepareStatement(create_users_table);
        usersStmt.executeUpdate();
        usersStmt.close();
        PreparedStatement gamesStmt = this.mConnection.prepareStatement(create_games_table);
        gamesStmt.executeUpdate();
        gamesStmt.close();
        PreparedStatement commandsStmt = this.mConnection.prepareStatement(create_commands_table);
        commandsStmt.executeUpdate();
        commandsStmt.close();
    }

    public void clearAllTables() {
        clearUsersTable();
        clearGamesTable();
        clearCommandsTable();
    }

    public void clearCommandsTable() {
        try {
            PreparedStatement dropUsersStmt = this.mConnection.prepareStatement("DROP TABLE IF EXISTS COMMANDS; ");
            dropUsersStmt.execute();
            dropUsersStmt.close();
        } catch (SQLException e) {
            System.out.println("Failed to clear users table");
            e.printStackTrace();
        }
    }

    public void clearGamesTable() {
        try {
            PreparedStatement dropUsersStmt = this.mConnection.prepareStatement("DROP TABLE IF EXISTS GAMES; ");
            dropUsersStmt.execute();
            dropUsersStmt.close();
        } catch (SQLException e) {
            System.out.println("Failed to clear users table");
            e.printStackTrace();
        }
    }

    public void clearUsersTable() {
        try {
            PreparedStatement dropUsersStmt = this.mConnection.prepareStatement("DROP TABLE IF EXISTS USERS; ");
            dropUsersStmt.execute();
            dropUsersStmt.close();
        } catch (SQLException e) {
            System.out.println("Failed to clear users table");
            e.printStackTrace();
        }
    }

    public void resetAllTables() {
        try {
            clearAllTables();
            createIfDoesntExist();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
