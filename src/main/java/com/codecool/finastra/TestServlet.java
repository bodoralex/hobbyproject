package com.codecool.finastra;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codecool.finastra.util.ConnUtil;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		writer.println("<!DOCTYPE html>");
		writer.println("<html>");
		writer.println("<body>");
		writer.println("<p>Hello Worl2222d!</p>");
		writer.println("</body>");
		writer.println("</html>");
		ConnUtil connUtil = new ConnUtil();
        Connection connection = connUtil.getConnection("testjob");
        String script = "SELECT * FROM users";
        try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(script);
			while (resultSet.next()) {			
				writer.println(resultSet.getString("username"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
