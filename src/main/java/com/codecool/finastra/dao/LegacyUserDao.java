package com.codecool.finastra.dao;

import com.codecool.finastra.exception.WrongUserNameOrPasswordException;
import com.codecool.finastra.models.User;

import java.sql.SQLException;

@Deprecated
public interface LegacyUserDao {
    User getUserByUserName(String username, String password) throws SQLException, WrongUserNameOrPasswordException;
}
