package com.codecool.finastra.dao;
//This class communicate with DB and set or get data from 'bankaccounts' table

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.codecool.finastra.models.BankAccount;
import com.codecool.finastra.util.ConnUtil;
import com.google.gson.Gson;

public class BankAccountDBDao {
	
	//Create connection with DB 'testjob' schema
	Connection connection = ConnUtil.getConnection("testjob");
	//I will add data to 'accounthistory' table so I create a new AccountHistoryDBDao instance
	private AccountHistoryDBDao accountHIstoryDBDao = new AccountHistoryDBDao();
	
	//Create a new bankAccount object and add this to bankAccounts list.
		private void addAccountsToArrayList(ArrayList<BankAccount> bankAccounts, ResultSet resultSet) throws SQLException {
			while(resultSet.next()){
				String accountNumber = resultSet.getString(1);
				String currency = resultSet.getString(2);
				int balance = resultSet.getInt(3);
				int userId = resultSet.getInt(4);
				
				bankAccounts.add(new BankAccount(accountNumber, currency, balance, userId));
			}
		}
	
	//Get details from 'bankaccount' table based on userId
	public String getBankAccountDetails(int id)throws SQLException{
		ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM `bankaccounts` WHERE user_id=?");
	   	statement.setInt(1, id);
	   	ResultSet resultSet = statement.executeQuery();
	   	addAccountsToArrayList(bankAccounts, resultSet);
			
		Gson gson = new Gson();
		return gson.toJson(bankAccounts);
	}
	
	//Get all bank account details from 'bankaccounts' table
	public String getAllBankAccounts() throws SQLException{
		
		ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM `bankaccounts`");
    	ResultSet resultSet = statement.executeQuery();
    	addAccountsToArrayList(bankAccounts, resultSet);

		Gson gson = new Gson();
		return gson.toJson(bankAccounts);
	}
	
	//Get bank account currency based on account number from 'bankaccounts' table
	public String getCurrency(String accountNumber) throws SQLException{
		String currency = null;

			PreparedStatement statement = connection.prepareStatement("SELECT currency FROM `bankaccounts` WHERE account_number=?");
			statement.setString(1, accountNumber);
	    	ResultSet resultSet = statement.executeQuery();
	    	if(resultSet.next()){
	    		currency = resultSet.getString(1);
	    		
	    	}
		
		return currency;
	}
	
	//Get bank account available balance based on account number from 'bankaccounts' table
	public int getBalance(String accountNumber) throws SQLException{
		int balance = 0;
		
		PreparedStatement statement = connection.prepareStatement("SELECT balance FROM `bankaccounts` WHERE account_number=?");
		statement.setString(1, accountNumber);
    	ResultSet resultSet = statement.executeQuery();
	    if(resultSet.next()){
	   		balance = resultSet.getInt(1);  		
	   	}
		
		return balance;
	}
	
	//Create transfer between 2 accounts
	//Update the source and the target account balance based on amount
	//Set auto commit to false so the statement isn't automatically committed after executeUodate() method
	//Commit all statement after I call connection.commit() method
	//In catch block I call connection.rollback(), so if something went wrong I cancel all the transactions
	public void createTransfer(String sourceAccount, String targetAccount, int amount) throws SQLException{
		
		PreparedStatement deductSource = null;
		PreparedStatement addTarget = null;
		String currency = getCurrency(sourceAccount);
		
		
		try {
			connection.setAutoCommit(false);
			deductSource = connection.prepareStatement("UPDATE `bankaccounts` SET balance = balance-? WHERE account_number=?");
			deductSource.setInt(1, amount);
			deductSource.setString(2, sourceAccount);
			accountHIstoryDBDao.addHistoryDetails(targetAccount, currency, amount, "deduction", sourceAccount);
			deductSource.executeUpdate();
			addTarget = connection.prepareStatement("UPDATE `bankaccounts` SET balance = balance+? WHERE account_number=?");
			addTarget.setInt(1, amount);
			addTarget.setString(2, targetAccount);
			accountHIstoryDBDao.addHistoryDetails(sourceAccount, currency, amount, "crediting", targetAccount);
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
