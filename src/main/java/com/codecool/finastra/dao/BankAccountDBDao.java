package com.codecool.finastra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.codecool.finastra.models.BankAccount;
import com.codecool.finastra.util.ConnUtil;
import com.google.gson.Gson;

public class BankAccountDBDao {
	
	Connection connection = ConnUtil.getConnection("testjob");
	
	public String getBankAccountDetails(int id){
		ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		
		try{
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM `bankaccounts` WHERE user_id=?");
	    	statement.setInt(1, id);
	    	ResultSet resultSet = statement.executeQuery();
	    	while(resultSet.next()){
	    		String accountNumber = resultSet.getString(1);
	    		String currency = resultSet.getString(2);
	    		int balance = resultSet.getInt(3);
	    		int userId = resultSet.getInt(4);
	    		
	    		bankAccounts.add(new BankAccount(accountNumber, currency, balance, userId));
	    	}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		return gson.toJson(bankAccounts);
	}
	
	public String getAllBankAccounts(){
		
		ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		
		try{
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM `bankaccounts`");
	    	ResultSet resultSet = statement.executeQuery();
	    	while(resultSet.next()){
	    		String accountNumber = resultSet.getString(1);
	    		String currency = resultSet.getString(2);
	    		int balance = resultSet.getInt(3);
	    		int userId = resultSet.getInt(4);
	    		
	    		bankAccounts.add(new BankAccount(accountNumber, currency, balance, userId));
	    	}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		return gson.toJson(bankAccounts);
	}


}
