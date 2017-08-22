/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author 102295
 */
public class mrps extends HttpServlet {

    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("username") != null && session.getAttribute("role") != null) {

            String username = session.getAttribute("username").toString();
            String role = session.getAttribute("role").toString();
            ResultSet r;

            try {
                r = SQLConnection.GetOnePara("procGetProjects", "--Select project--");
                while (r.next()) {
                    request.setAttribute("Projects", r.getString("Projects"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(anu.class.getName()).log(Level.SEVERE, null, ex);
            }

            String rooturl = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().indexOf("com.EmployeeTimeCapsule.TimeCapsule")) + "com.EmployeeTimeCapsule.TimeCapsule/";
            request.setAttribute("url", rooturl);

            switch (role) {
                case "Manager":
                    request.getRequestDispatcher("/WEB-INF/views/mrps.jsp").forward(request, response);
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

        if (session != null && session.getAttribute("username") != null && session.getAttribute("role") != null) {

            String username = session.getAttribute("username").toString();
            String role = session.getAttribute("role").toString();
            ResultSet r;
            String ReportTasksProgress = "";

            try {
                r = SQLConnection.GetOnePara("procGetProjects", request.getParameter("pjtn"));
                while (r.next()) {
                    request.setAttribute("Projects", r.getString("Projects"));
                }
                
                r = SQLConnection.GetTwoPara("procReportTaskProgressDash", username,request.getParameter("pjtn"));
                while (r.next()) {
                    request.setAttribute("Tasks", r.getString("Tasks"));
                }
                
                r = SQLConnection.GetTwoPara("procReportTaskProjectsProgress", username,request.getParameter("pjtn"));
                while (r.next()) {
                     ReportTasksProgress = ReportTasksProgress + " <tr><td>" + r.getString("ProjectTaskName") + "</td><td>"+
                                                                                    r.getString("ProjectTaskHours") +"</td><td>"+
                                                                                    r.getString("Hours") +"</td><td>"+
                                                                                    r.getString("Completion") +"</td></tr>";
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(anu.class.getName()).log(Level.SEVERE, null, ex);
            }

            String rooturl = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().indexOf("com.EmployeeTimeCapsule.TimeCapsule")) + "com.EmployeeTimeCapsule.TimeCapsule/";
            request.setAttribute("url", rooturl);
 request.setAttribute("ReportTasksProgress", ReportTasksProgress);
            switch (role) {
                case "Manager":
                    request.getRequestDispatcher("/WEB-INF/views/mrps.jsp").forward(request, response);
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
    public String getServletInfo() {
        return "Short description";
    }

}
