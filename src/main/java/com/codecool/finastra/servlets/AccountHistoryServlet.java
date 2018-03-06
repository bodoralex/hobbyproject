package com.codecool.finastra.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codecool.finastra.dao.AccountHistoryDBDao;

@WebServlet("/accounthistory")
public class AccountHistoryServlet extends HttpServlet{
	
	private AccountHistoryDBDao accountHistoryDBDao = new AccountHistoryDBDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accountNumber = req.getParameter("accountNumber");
		String result = accountHistoryDBDao.getHistoryDetails(accountNumber);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(result);
		out.close();
	}

}
