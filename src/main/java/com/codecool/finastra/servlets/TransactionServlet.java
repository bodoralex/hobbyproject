package com.codecool.finastra.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;

import com.codecool.finastra.dao.BankAccountDBDao;

@WebServlet("/transactionpage")
public class TransactionServlet extends HttpServlet{
	
	private BankAccountDBDao bankAccountDBDao = new BankAccountDBDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String result = bankAccountDBDao.getAllBankAccounts();
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(result);
		out.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = session.getAttribute("id").toString();
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(id);
		out.close();
		
	}

}
