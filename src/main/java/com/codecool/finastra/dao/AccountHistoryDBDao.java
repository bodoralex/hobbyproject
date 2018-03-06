package com.codecool.finastra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.codecool.finastra.models.AccountHistory;
import com.codecool.finastra.util.ConnUtil;
import com.google.gson.Gson;

public class AccountHistoryDBDao {
	
	Connection connection = ConnUtil.getConnection("testjob");
	
	public void addHistoryDetails(String sourceTargetAccount, String currency, 
					int amount, String transactionType, String accountNumber){
		try{
				PreparedStatement statement = connection.prepareStatement("INSERT INTO accounthistory VALUES (0, ?, ?, ?, ?, ?)");
				statement.setString(1, sourceTargetAccount);
				statement.setString(2, currency);
				statement.setInt(3, amount);
				statement.setString(4, transactionType);
				statement.setString(5, accountNumber);
				statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getHistoryDetails(String accountNumber){
		ArrayList<AccountHistory> accountHistories = new ArrayList<AccountHistory>();
		
		try{
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounthistory");
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				int accountHistoryId = resultSet.getInt(1);
				String sourceTargetAccount = resultSet.getString(2);
				String currency = resultSet.getString(3);
				int amount = resultSet.getInt(4);
				String transactionType = resultSet.getString(5);
				String accountnumber = resultSet.getString(6);
				accountHistories.add(new AccountHistory(accountHistoryId, sourceTargetAccount, currency, amount, transactionType, accountnumber));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		return gson.toJson(accountHistories);
	}

}
