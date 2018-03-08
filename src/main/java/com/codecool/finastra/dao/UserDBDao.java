package com.codecool.finastra.dao;
//This class communicate with DB and set or get data from 'users' table

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codecool.finastra.models.User;
import com.codecool.finastra.util.ConnUtil;
import com.google.gson.Gson;

public class UserDBDao {
	
	//Create connection with DB 'testjob' schema
    Connection connection = ConnUtil.getConnection("testjob");
    
    //Get user based on user name and
    //If the password is equal to password saved in db, return user
    //Return error message if the passwords are not the same
    public String getUser(String username, String password){
    	User user = new User(0, "","");
    	Gson gson = new Gson();
    	
    	try {
	    	PreparedStatement statement = connection.prepareStatement("SELECT * FROM `users` WHERE username=?");
	    	statement.setString(1, username);
	    	ResultSet resultSet = statement.executeQuery();
	    	if (resultSet.next()) {
	    		Integer userID = resultSet.getInt(1);
	            String userName = resultSet.getString(2);
	            String pass = resultSet.getString(3);
	            
	            user = new User(userID, userName, pass);
	    		}
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    	if (user.getPassword().equals(password)) {
    		return gson.toJson(user);
    	}
    	return gson.toJson(new User(0, "", ""));
    }

}
