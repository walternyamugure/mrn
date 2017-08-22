package com.EmployeeTimeCapsule;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet(urlPatterns="/login")
public class login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Close a session         
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        session.removeAttribute("role");
        session.removeAttribute("timesheetid");

//        response.setHeader("Cache-Control", "no-cache");
//        response.setHeader("Cache-Control", "no-store");
//        response.setHeader("Pragma", "no-cache");
//        response.setDateHeader("Expires", 0);
        
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role;
        role = "";

        try {
            role = SQLConnection.GetOneColTwoPara("procGetUserRole", "Role", username, password);
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }

        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("role", role);
        session.setAttribute("timesheetid", 0);
           

        String rooturl = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().indexOf("com.EmployeeTimeCapsule.TimeCapsule")) + "com.EmployeeTimeCapsule.TimeCapsule/";
        request.setAttribute("url", rooturl);

        switch (role) {
            case "User":
                response.sendRedirect(rooturl + "dashuser");
                break;
            case "Admin":
                response.sendRedirect(rooturl + "anu");
                break;
            case "Manager":
                response.sendRedirect(rooturl + "dashmng");
                break;
            default:
                request.setAttribute("error", "Invalid user login details.");
                request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
                break;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
