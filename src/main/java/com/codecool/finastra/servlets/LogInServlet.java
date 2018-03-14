package com.codecool.finastra.servlets;
//This servlet communicate with db users table

import com.codecool.finastra.dao.UserDbDao;
import com.codecool.finastra.exception.WrongUserNameOrPasswordException;
import com.codecool.finastra.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {

    private UserDbDao userDbDao = new UserDbDao();

    //Get username and password from frontend
    //Cast result to User object
    //If User object's password not equal ""
    //Create Session, set attribute id equals to user's id in db
    //Send response to clients side

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        try {
            User user = userDbDao.getUser(req.getParameter("username"), req.getParameter("password"));

            req.getSession().setAttribute("id", user.getUserId());
            out.write("ok");

        } catch (WrongUserNameOrPasswordException | SQLException e) {
            e.printStackTrace();
            out.write("error");
        } finally {
            out.close();
        }
    }
}
