package com.EmployeeTimeCapsule;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class map extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("username") != null && session.getAttribute("role") != null) {

            String username = session.getAttribute("username").toString();
            String role = session.getAttribute("role").toString();

            String rooturl = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().indexOf("com.EmployeeTimeCapsule.TimeCapsule")) + "com.EmployeeTimeCapsule.TimeCapsule/";
            request.setAttribute("url", rooturl);

            ResultSet r;

            try {
                r = SQLConnection.GetZeroPara("procGetUnAssignedProjects");
                while (r.next()) {
                    request.setAttribute("Projects", r.getString("Projects"));
                }

                r = SQLConnection.GetOnePara("procGetAssignedProjects", username);
                while (r.next()) {
                    request.setAttribute("AssignedProjects", r.getString("AssignedProjects"));
                }

            } catch (SQLException ex) {
                Logger.getLogger(anu.class.getName()).log(Level.SEVERE, null, ex);
            }
            switch (role) {
                case "Manager":
                    request.getRequestDispatcher("/WEB-INF/views/map.jsp").forward(request, response);
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
        ResultSet r = null;
        String res = "";
        String username = session.getAttribute("username").toString();

        if (session != null && session.getAttribute("username") != null && session.getAttribute("role") != null) {

            try {
                if (request.getParameter("asgnprj") != null) {
                    SQLConnection.GetTwoPara("procAssignProject", request.getParameter("allpjcts"), username);
                } else {
                    SQLConnection.GetOnePara("procUnAssignProject", request.getParameter("myprjcts"));
                }

                r = SQLConnection.GetZeroPara("procGetUnAssignedProjects");
                while (r.next()) {
                    request.setAttribute("Projects", r.getString("Projects"));
                }

                r = SQLConnection.GetOnePara("procGetAssignedProjects", username);
                while (r.next()) {
                    request.setAttribute("AssignedProjects", r.getString("AssignedProjects"));
                }

                request.setAttribute("ResultMessage", res);
                request.getRequestDispatcher("/WEB-INF/views/map.jsp").forward(request, response);

            } catch (SQLException ex) {
                Logger.getLogger(anu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String rooturl = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().indexOf("com.EmployeeTimeCapsule.TimeCapsule")) + "com.EmployeeTimeCapsule.TimeCapsule/";
            request.setAttribute("url", rooturl);
            response.sendRedirect(rooturl + "login");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
