package com.codecool.finastra.servlets;
//This servlet communicate with db bankaccount table

import com.codecool.finastra.dao.BankAccountDBDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/bankaccount")
public class BankAccountsServlet extends HttpServlet {

    private BankAccountDBDao bankAccountDBDao = new BankAccountDBDao();

    //From the session I get the user's Id
    //Based on this Id get the data bankAccount table
    //Send the data to clients side
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        int id = (Integer) session.getAttribute("id");

        String result;
        try {
            result = bankAccountDBDao.getBankAccountDetails(id);
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            out.write(result);
            out.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
