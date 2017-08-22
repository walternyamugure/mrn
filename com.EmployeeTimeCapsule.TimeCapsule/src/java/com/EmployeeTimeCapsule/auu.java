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

public class auu extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("username") != null && session.getAttribute("role") != null) {

            String username = session.getAttribute("username").toString();
            String role = session.getAttribute("role").toString();

            ResultSet r = null;

            String rooturl = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().indexOf("com.EmployeeTimeCapsule.TimeCapsule")) + "com.EmployeeTimeCapsule.TimeCapsule/";
            request.setAttribute("url", rooturl);

            switch (role) {
                case "Admin":
                    request.getRequestDispatcher("/WEB-INF/views/auu.jsp").forward(request, response);
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
        
        if (session != null && session.getAttribute("username") != null && session.getAttribute("role") != null) {

            try {
                if (request.getParameter("save") != null) {
                    
                    r = SQLConnection.UpdateElevenPara("procUpdUserDetails",request.getParameter("username"),request.getParameter("name"),request.getParameter("surname"),request.getParameter("pass"),request.getParameter("confirmpass"),request.getParameter("role"),request.getParameter("emptyp"),request.getParameter("email"),request.getParameter("department"),request.getParameter("active"),request.getParameter("costcenter"));
                    r.next();
                    request.setAttribute("ResultMessage", r.getString("ResultMessage")); 
                    
                    r = SQLConnection.GetOnePara("procGetUser", request.getParameter("username"));
                    r.next();
                    request.setAttribute("UserID", r.getString("UserID"));
                    request.setAttribute("Name", r.getString("Name"));
                    request.setAttribute("Surname", r.getString("Surname"));
                    request.setAttribute("Password", r.getString("Password"));
                    request.setAttribute("Email", r.getString("Email"));
                    request.setAttribute("Department", r.getString("Department"));
                    request.setAttribute("EmploymentType", r.getString("EmploymentType"));
                    request.setAttribute("Role", r.getString("Role"));
                    request.setAttribute("CostCenter", r.getString("CostCenter"));
                                
                }
                else{
                    r = SQLConnection.GetOnePara("procGetUser", request.getParameter("search"));
                    r.next();
                    request.setAttribute("UserID", r.getString("UserID"));
                    request.setAttribute("Name", r.getString("Name"));
                    request.setAttribute("Surname", r.getString("Surname"));
                    request.setAttribute("Password", r.getString("Password"));
                    request.setAttribute("Email", r.getString("Email"));
                    request.setAttribute("Department", r.getString("Department"));
                    request.setAttribute("EmploymentType", r.getString("EmploymentType"));
                    request.setAttribute("Role", r.getString("Role"));
                    request.setAttribute("CostCenter", r.getString("CostCenter"));
                    request.setAttribute("ResultMessage", r.getString("ResultMessage"));                    
                }
                
                request.getRequestDispatcher("/WEB-INF/views/auu.jsp").forward(request, response);

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
