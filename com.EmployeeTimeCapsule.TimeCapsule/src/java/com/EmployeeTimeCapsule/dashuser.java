package com.EmployeeTimeCapsule;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = ("/dashuser"))
public class dashuser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("username") != null && session.getAttribute("role") != null) {
            String username = session.getAttribute("username").toString();
            String role = session.getAttribute("role").toString();

            ResultSet r = null;
            String approved = "";
            String pending = "";
            String rejected = "";
            String outstanding = "";
            try {
                r = SQLConnection.GetFourColOnePara("procGetUserAggregatedTimesheetsStatuses", "Approved", "Pending", "Rejected", "Outstanding", username);
                while (r.next()) {
                    approved = r.getString("Approved");
                    pending = r.getString("Pending");
                    rejected = r.getString("Rejected");
                    outstanding = r.getString("Outstanding");
                }
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }

            String rooturl = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().indexOf("com.EmployeeTimeCapsule.TimeCapsule")) + "com.EmployeeTimeCapsule.TimeCapsule/";
            request.setAttribute("url", rooturl);

            switch (role) {
                case "User":
                    request.setAttribute("Approved", approved);
                    request.setAttribute("Pending", pending);
                    request.setAttribute("Rejected", rejected);
                    request.setAttribute("Outstanding", outstanding);
                    request.getRequestDispatcher("/WEB-INF/views/dashuser.jsp").forward(request, response);
                    break;
                default:
                    request.setAttribute("error", "Session expired. Re-login.");
                    response.sendRedirect(rooturl + "login");
                    break;
            }
        } else {
            String rooturl = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().indexOf("com.EmployeeTimeCapsule.TimeCapsule")) + "com.EmployeeTimeCapsule.TimeCapsule/";
            request.setAttribute("url", rooturl);
            response.sendRedirect(rooturl + "login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null || session.getAttribute("username") != null) {
            String rooturl = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().indexOf("com.EmployeeTimeCapsule.TimeCapsule")) + "com.EmployeeTimeCapsule.TimeCapsule/";
            response.sendRedirect(rooturl + "login");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
