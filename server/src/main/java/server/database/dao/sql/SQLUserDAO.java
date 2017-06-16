package server.database.dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.database.dao.iUserDAO;
import shared.classes.User;

/**
 * Created by Dylan on 6/15/2017.
 */

public class SQLUserDAO implements iUserDAO {

    public SQLUserDAO() {

    }

    public static boolean isUserDup(String input) {
        User user = null;
        user = getUserByUserName(input);
        if (user != null) {
            return true;
        }
        return false;
    }

    private static User getUserByUserName(String name) {
        if (name == null) {
//            System.out.println("yep");
            return null;
        }
        String selectUserStmt = "SELECT * FROM USERS WHERE USERS.userName = '" + name + "'";

        User output = new User("","");
        SQLDatabaseConnection db = new SQLDatabaseConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            db.openConnection();

            preparedStatement = db.mConnection.prepareStatement(selectUserStmt);
            resultSet = preparedStatement.executeQuery();

            output.setUsername(resultSet.getString("userName"));
            output.setPassword(resultSet.getString("password"));
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

    public static void addUserToDb(User user) {
        String userAddString = "INSERT INTO USERS " +
                "(userName, password) " +
                "VALUES (" +
                "'" + user.getUsername() + "', " +
                "'" + user.getPassword() + "', " +
                ")";

        SQLDatabaseConnection db = new SQLDatabaseConnection();

        try {
            db.openConnection();
            PreparedStatement addUserStmt = db.mConnection.prepareStatement(userAddString);
            addUserStmt.execute();
            addUserStmt.close();
            db.closeConnection();

        } catch (SQLException e) {
            System.out.println("failed to add user");
        }

    }



    @Override
    public boolean create() {
        return true;
    }

    @Override
    public User read() {
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
