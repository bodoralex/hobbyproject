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

@WebServlet("/transactionpage")
public class TransactionServlet extends HttpServlet {

    //Create bankAccountDBDao instance because I want to communicate with db
    private BankAccountDBDao bankAccountDBDao = new BankAccountDBDao();

    //Get all bank account details from db, and send this to clients side
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result;
        try {
            result = bankAccountDBDao.getAllBankAccounts();
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            out.write(result);
            out.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Get user's iD from session and send this to clients side
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
