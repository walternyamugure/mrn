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

public class mup extends HttpServlet {

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
                    request.getRequestDispatcher("/WEB-INF/views/mup.jsp").forward(request, response);
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

                    r = SQLConnection.UpdateFivePara("procUpdateProject", request.getParameter("prjts"), request.getParameter("prjtnm"), request.getParameter("prjtown"), request.getParameter("prjtcc"), request.getParameter("prjtsts"));
                    while (r.next()) {
                        request.setAttribute("ResultMessage", r.getString("ResultMessage"));
                    }
                    
                    r = SQLConnection.UpdateFivePara("procUpdateProjectTask", request.getParameter("prjts"), request.getParameter("prjtsks"), request.getParameter("tskn"), request.getParameter("tskhrs"), request.getParameter("stdt"));
                    while (r.next()) {
                        //request.setAttribute("ResultMessage", r.getString("ResultMessage"));
                    }
                }
                else if (request.getParameter("remove") != null){
                r = SQLConnection.UpdateTwoPara("procDeleteProjectTask", request.getParameter("prjts"), request.getParameter("tskn"));
                    while (r.next()) {
                        request.setAttribute("ResultMessage", r.getString("ResultMessage"));
                    }
                }
                
                //Get list of projects
                r = SQLConnection.GetOnePara("procGetProjects", request.getParameter("prjts"));
                while (r.next()) {
                    request.setAttribute("Projects", r.getString("Projects"));
                }

                //Get selected project details
                r = SQLConnection.GetOnePara("procGetProjectDetails", request.getParameter("prjts"));
                while (r.next()) {
                    request.setAttribute("ProjectName", r.getString("ProjectName"));
                    request.setAttribute("ProjectCostCenter", r.getString("ProjectCostCenter"));
                    request.setAttribute("ProjectOwner", r.getString("ProjectOwner"));
                    request.setAttribute("Status", r.getString("Status"));
                }

                String tsk = request.getParameter("prjtsks") != null ? (request.getParameter("prjtsks").contains("No task(s) exist") ? "" : request.getParameter("prjtsks")) : "";

                //Get selected project tasks
                r = SQLConnection.GetTwoPara("procGetProjectTasks", request.getParameter("prjts"), tsk);
                while (r.next()) {
                    request.setAttribute("ProjectTasks", r.getString("ProjectTasks"));
                }

                //Get selected project task details
                r = SQLConnection.GetTwoPara("procGetProjectTaskDetails", request.getParameter("prjtsks"), request.getParameter("prjtnm"));
                while (r.next()) {
                    request.setAttribute("ProjectTaskName", r.getString("ProjectTaskName"));
                    request.setAttribute("ProjectTaskHours", r.getString("ProjectTaskHours"));
                    request.setAttribute("ProjectTaskStartDate", r.getString("ProjectTaskStartDate"));
                }

                request.getRequestDispatcher("/WEB-INF/views/mup.jsp").forward(request, response);
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
