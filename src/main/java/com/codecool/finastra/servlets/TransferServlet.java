package com.codecool.finastra.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codecool.finastra.dao.AccountHistoryDBDao;
import com.codecool.finastra.dao.BankAccountDBDao;

@WebServlet("/transfer")
public class TransferServlet extends HttpServlet{
	
	private BankAccountDBDao bankAccountDBDao = new BankAccountDBDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Get the details from clients side.
		String source = req.getParameter("source").toString();
		String target = req.getParameter("target").toString();
		
		//Check the source and the target account's is the same or not.
		String sourceCurrency = bankAccountDBDao.getCurrency(source);
		String targetCurrency = bankAccountDBDao.getCurrency(target);
		
		//Check the source account had enough money for the transaction.
		int balance = bankAccountDBDao.getBalance(source);
		
		PrintWriter out = resp.getWriter();
		try	{
			int amount = Integer.parseInt(req.getParameter("amount"));
			
			if(!sourceCurrency.equals(targetCurrency)){
				out.write("Transaction error. The currencies are not the same.");
				out.close();
			} else if(balance-amount < 0){
				out.write("Transaction error. Source account not enough balance for this transaction.");
				out.close();
			} else {
				bankAccountDBDao.createTransfer(source, target, amount);
				out.write("Transaction completed.");
				out.close();
			}
		}catch (NumberFormatException e) {
			out.write("Amount must be integer.");
			out.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
