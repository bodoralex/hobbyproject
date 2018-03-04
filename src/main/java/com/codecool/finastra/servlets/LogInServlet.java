package com.codecool.finastra.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.codecool.finastra.dao.UserDBDao;
import com.codecool.finastra.util.ConnUtil;
import com.google.gson.JsonObject;

import org.json.*;

@WebServlet("/login")
public class LogInServlet extends HttpServlet{
	
	private UserDBDao userDBDao = new UserDBDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Get username and password from frontend
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String result = userDBDao.getUser(username, password);
		//Create Session
		if(!result.equals("\"notok\"")){
			//Get id from JSONObject
			JSONObject jsonObject = new JSONObject(result);
			int id = jsonObject.getInt("userId");
			HttpSession session = req.getSession();
			session.setAttribute("id", id);
			
		}
		//Send response to frontend
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(result);
		out.close();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
