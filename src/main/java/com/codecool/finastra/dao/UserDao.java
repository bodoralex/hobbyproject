package com.codecool.finastra.dao;

import com.codecool.finastra.models.User;


public interface UserDao {
    User getUserByName(String name);

    User getUserById(int id);

    User registerUser(User user);

    User updateUser(User user);
}
