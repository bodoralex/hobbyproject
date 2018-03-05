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
	
	public String getCurrency(String accountNumber){
		String currency = null;
		try{
			PreparedStatement statement = connection.prepareStatement("SELECT currency FROM `bankaccounts` WHERE account_number=?");
			statement.setString(1, accountNumber);
	    	ResultSet resultSet = statement.executeQuery();
	    	if(resultSet.next()){
	    		currency = resultSet.getString(1);
	    		
	    	}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return currency;
	}
	
	public int getBalance(String accountNumber){
		int balance = 0;
		try{
			PreparedStatement statement = connection.prepareStatement("SELECT balance FROM `bankaccounts` WHERE account_number=?");
			statement.setString(1, accountNumber);
	    	ResultSet resultSet = statement.executeQuery();
	    	if(resultSet.next()){
	    		balance = resultSet.getInt(1);
	    		
	    	}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return balance;
	}
	
	public void createTransfer(String sourceAccount, String targetAccount, int amount) throws SQLException{
		
		PreparedStatement deductSource = null;
		PreparedStatement addTarget = null;
		
		
		try {
			connection.setAutoCommit(false);
			deductSource = connection.prepareStatement("UPDATE `bankaccounts` SET balance = balance-? WHERE account_number=?");
			deductSource.setInt(1, amount);
			deductSource.setString(2, sourceAccount);
			deductSource.executeUpdate();
			addTarget = connection.prepareStatement("UPDATE `bankaccounts` SET balance = balance+? WHERE account_number=?");
			addTarget.setInt(1, amount);
			addTarget.setString(2, targetAccount);
			addTarget.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			if (deductSource != null) {
				deductSource.close();
			}

			if (addTarget != null) {
				addTarget.close();
			}
		}
	}


}