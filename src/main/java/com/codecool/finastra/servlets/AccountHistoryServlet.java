package com.codecool.finastra.servlets;
//This servlet communicate with db accounthistory table

import com.codecool.finastra.dao.AccountHistoryDbDao;
import com.codecool.finastra.models.AccountHistory;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/accounthistory")
public class AccountHistoryServlet extends HttpServlet {

    private AccountHistoryDbDao accountHistoryDbDao = new AccountHistoryDbDao();

    //Get the account number from request
    //Send the history details to clients side based on account number
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accountNumber = req.getParameter("accountNumber");
        List<AccountHistory> result;
        try {
            result = accountHistoryDbDao.getHistoryDetails();
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            out.write(new Gson().toJson(result));
            out.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
