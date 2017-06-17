package server.database.dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public boolean create(User user) {
        addUserToDb(user);
        return true;
    }

    @Override
    public Set<User> read() {
        SQLDatabaseConnection db = new SQLDatabaseConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String selectUserStmt = "SELECT * FROM USERS";
        Set<User> output = new HashSet<>();


        try {
            db.openConnection();

            preparedStatement = db.mConnection.prepareStatement(selectUserStmt);
            resultSet = preparedStatement.executeQuery();
            output.addAll(getSetOfUsersFromRS(resultSet));


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

    private Collection<? extends User> getSetOfUsersFromRS(ResultSet resultSet) {
        Collection<User> output = new HashSet<>();
        try {
            while (resultSet.next()) {
                output.add(new User(resultSet.getString("userName"),resultSet.getString("password")));
            }
        } catch (SQLException e) {
            System.out.println("Unable to get from Result set");
            e.printStackTrace();
        }
        return output;
    }


    @Override
    public boolean update() {
        return true;
    }

    @Override
    public boolean delete() {
        SQLDatabaseConnection db = new SQLDatabaseConnection();
        db.openConnection();
        db.clearUsersTable();
        db.closeConnection();
        return true;
    }
}
