package com.codecool.finastra.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.codecool.finastra.dao.BankAccountDBDao;

@WebServlet("/bankaccount")
public class BankAccountsServlet extends HttpServlet{
	
	BankAccountDBDao bankAccountDBDao = new BankAccountDBDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Create session
		HttpSession session = req.getSession();
		//Get the id from session
		int id = (Integer) session.getAttribute("id");
		//Get data from DB.
		String result = bankAccountDBDao.getBankAccountDetails(id);
		//Send response to frontend
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(result);
		out.close();
	}

}
