package com.codecool.finastra.dao;
//This class communicate with DB and set or get data from 'users' table

import com.codecool.finastra.exception.WrongUserNameOrPasswordException;
import com.codecool.finastra.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Deprecated
public class UserDbDao extends DbDao implements LegacyUserDao {

    /**
     * description:
     * Get user based on user name and
     * If the password is equal to password saved in db, return user
     * Return error message if the passwords are not the same
     *
     * @param username The user's name
     * @param password The user's password
     * @return Convert data to Json and return with this.
     * @throws SQLException
     */
    @Override
    public User getUserByUserName(String username, String password) throws SQLException, WrongUserNameOrPasswordException {
        User user = new User();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM `users` WHERE username=?");
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Integer userID = resultSet.getInt(1);
            String userName = resultSet.getString(2);
            String pass = resultSet.getString(3);

            user = new User(userID, userName, pass);
        }

        if (user.getPassword().equals(password)) {
            return user;
        }
        throw new WrongUserNameOrPasswordException();
    }

}
