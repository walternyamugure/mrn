/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class uts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("username") != null && session.getAttribute("username") != null) {
            String username = session.getAttribute("username").toString();
            String typ = request.getParameter("typ");
            String html = "";
            ResultSet r = null;

            String rooturl = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().indexOf("com.EmployeeTimeCapsule.TimeCapsule")) + "com.EmployeeTimeCapsule.TimeCapsule/";
            request.setAttribute("url", rooturl);

            try {               
                r = SQLConnection.GetFiveColTwoPara("procGetMyTimesheets", "Cnt", "TimeSheetID", "Period", "Status", "Comment", username, typ);
                
                while (r.next()) {
                    html = html + " <tr><td>" + r.getString("Cnt") + "</td><td><a href=" + rooturl + "unts?id=" + r.getString("TimeSheetID") + ">" + r.getString("Period") + "</a></td><td>" + r.getString("Status") + "</td><td>" + r.getString("Comment") + "</td></tr>";

                }
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                html = "Error retrieving records. Contact system Administrator.";
            }

            request.setAttribute("typ", typ);
            request.setAttribute("html", html);
            request.setAttribute("url", rooturl);
            request.getRequestDispatcher("/WEB-INF/views/uts.jsp").forward(request, response);
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
