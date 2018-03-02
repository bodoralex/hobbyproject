package com.codecool.finastra.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codecool.finastra.dao.UserDBDao;
import com.codecool.finastra.util.ConnUtil;

@WebServlet("/login")
public class LogInServlet extends HttpServlet{
	
	private UserDBDao userDBDao = new UserDBDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		userDBDao.getUser(username, password);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(userDBDao.getUser(username, password));
		out.close();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
