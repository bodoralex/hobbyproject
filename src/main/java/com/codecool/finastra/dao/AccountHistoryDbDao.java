package com.codecool.finastra.dao;

//This class communicate with DB and set or get data from 'accounthistory' table
import com.codecool.finastra.models.AccountHistory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountHistoryDbDao extends DbDao {

    /**
     * description:
     * Add row to 'accounthistory' table.
     *
     * @param sourceTargetAccount: The other customer's account number.
     * @param currency:            The account's currency.
     * @param amount:              The transfer amount.
     * @param transactionType:     Deduction or crediting.
     * @param accountNumber:       the number of the given account
     * @throws SQLException
     */
    public void addHistoryDetails(String sourceTargetAccount, String currency,
                                  int amount, String transactionType, String accountNumber) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("INSERT INTO accounthistory VALUES (0, ?, ?, ?, ?, ?)");
        statement.setString(1, sourceTargetAccount);
        statement.setString(2, currency);
        statement.setInt(3, amount);
        statement.setString(4, transactionType);
        statement.setString(5, accountNumber);
        statement.executeUpdate();
    }

    /**
     * description:
     * Get row(s) from 'accounthistory' table based on account number.
     *
     * @return Convert data to Json and return with this.
     * Doing this  every dbdao method because it's easier to handle the data on client side.
     * @throws SQLException
     */
    public List<AccountHistory> getHistoryDetails() throws SQLException {
        ArrayList<AccountHistory> accountHistories = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounthistory");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int accountHistoryId = resultSet.getInt(1);
            String sourceTargetAccount = resultSet.getString(2);
            String currency = resultSet.getString(3);
            int amount = resultSet.getInt(4);
            String transactionType = resultSet.getString(5);
            String accountNumber = resultSet.getString(6);
            accountHistories.add(new AccountHistory(accountHistoryId, sourceTargetAccount, currency, amount, transactionType, accountNumber));
        }
        return accountHistories;
    }
}
