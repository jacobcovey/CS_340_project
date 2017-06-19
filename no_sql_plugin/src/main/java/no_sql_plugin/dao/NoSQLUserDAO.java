package no_sql_plugin.dao;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import shared.interfaces.dao.iUserDAO;
import shared.classes.User;

public class NoSQLUserDAO extends JSONDatabase implements iUserDAO {

    public NoSQLUserDAO() {
        super("database/users.json");
    }

    @Override
    public boolean create(User user) {
        Set<User> users = read();
        users.add(user);
        return writeToDb(gson.toJson(users));
    }

    @Override
    public Set<User> read() {
        String usersString;
        try {
            usersString = readFromDb();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        User[] users = gson.fromJson(usersString, User[].class);
        return new HashSet<>(Arrays.asList(users));
    }

    @Override
    public boolean clear() {
        return writeToDb("[]");
    }


    @Override
    public boolean delete() {
        return true;
    }

    @Override
    public boolean update() {
        return true;
    }
}
