package com.codecool.finastra.models;

//This class created based on database users table
//I create setters and getters all parameters

public class User {

    private int userId;
    private String username;
    private String password;
    private String hashedPassword;


    public User(int userId, String username, String plainPassword) {
        super();
        this.userId = userId;
        this.username = username;
        this.password = plainPassword;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

}
