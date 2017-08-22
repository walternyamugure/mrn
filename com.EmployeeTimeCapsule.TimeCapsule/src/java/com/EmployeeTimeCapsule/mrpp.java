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

public class mrpp extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("username") != null && session.getAttribute("role") != null) {
            String username = session.getAttribute("username").toString();
            String role = session.getAttribute("role").toString();
            String ReportProjectsProgress = "";
            ResultSet r = null;
            try {
                r = SQLConnection.GetOnePara("procReportDetailedProjectsProgress", username);
                while (r.next()) {
                    ReportProjectsProgress = ReportProjectsProgress + " <tr><td>" + r.getString("ProjectName") + "</td><td>"+
                                                                                    r.getString("ProjectTaskName") +"</td><td>"+
                                                                                    r.getString("ProjectTaskHours") +"</td><td>"+
                                                                                    r.getString("HoursUsed") +"</td><td>"+
                                                                                    r.getString("HoursLeft") +"</td><td>"+
                                                                                    r.getString("PercentageCompletion") +"</td><td>"+
                                                                                    r.getString("PlannedFinishDate") +"</td><td>"+
                                                                                    r.getString("ProjectedFinishDate") +"</td><td>"+
                                                                                    r.getString("HoursVariance") +"</td></tr>";
                }
                
               
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }

            String rooturl = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().indexOf("com.EmployeeTimeCapsule.TimeCapsule")) + "com.EmployeeTimeCapsule.TimeCapsule/";
            request.setAttribute("url", rooturl);
            request.setAttribute("ReportProjectsProgress", ReportProjectsProgress);

            switch (role) {
                case "Manager":
                    request.getRequestDispatcher("/WEB-INF/views/mrpp.jsp").forward(request, response);
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

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
