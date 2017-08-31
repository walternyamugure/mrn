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

public class mnts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null && session.getAttribute("role") != null) {
            String username = session.getAttribute("username").toString();
            if (request.getParameter("id") != null) {
                //if (request.getParameter("id").trim() == "") {
                session.setAttribute("timesheetid", request.getParameter("id"));
                //}
            }

            String timeshetID = session.getAttribute("timesheetid").toString();

            ResultSet r = null;
            ResultSet pj = null;
            int cnt = 1;
            String val;

            try {
                r = SQLConnection.GetOnePara("procGetWeekPeriods ", timeshetID);
                r.next();
                request.setAttribute("Periods", r.getString("Periods"));

                r = SQLConnection.GetOnePara("procGetTimesheetWeek", timeshetID);
                r.next();
                session.setAttribute("selectedwk", r.getString("WeekNumber"));

                r = SQLConnection.GetOnePara("procGetWeekDays", "0");
                r.next();
                session.setAttribute("weekDays", r.getString("WeekDays"));

                r = SQLConnection.GetOnePara("procGetProjects", "--Select project--");
                r.next();
                request.setAttribute("prj", r.getString("Projects"));

                if (request.getParameter("id") == null) {

                } else {
                    String sun = "";
                    String mon = "";
                    String tue = "";
                    String wed = "";
                    String thur = "";
                    String fri = "";
                    String sat = "";
                    r = SQLConnection.GetOnePara("procGetCapturedProjectsAndTasks ", timeshetID);
                    while (r.next()) {
                        switch (r.getInt("WeekDayNameID")) {
                            case 1:
                                sun = sun + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                                request.setAttribute("prjtskSun", sun);
                                break;
                            case 2:
                                mon = mon + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                                request.setAttribute("prjtskMon", mon);
                                break;
                            case 3:
                                tue = tue + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                                request.setAttribute("prjtskTue", tue);
                                break;
                            case 4:
                                wed = wed + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                                request.setAttribute("prjtskWed", wed);
                                break;
                            case 5:
                                thur = thur + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                                request.setAttribute("prjtskThur", thur);
                                break;
                            case 6:
                                fri = fri + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                                request.setAttribute("prjtskFri", fri);
                                break;
                            case 7:
                                sat = sat + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                                request.setAttribute("prjtskSat", sat);
                                break;
                            default:
                                break;
                        }
                    }
                }
                r = SQLConnection.GetEightColTwoPara("procGetTimeshetDetails ", "Order", "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat", timeshetID, username);
                while (r.next()) {
                    if ((r.getString("Order")).equals(Integer.toString(cnt))) {
                        request.setAttribute("Sun" + cnt, r.getString("Sun"));
                        request.setAttribute("Mon" + cnt, r.getString("Mon"));
                        request.setAttribute("Tue" + cnt, r.getString("Tue"));
                        request.setAttribute("Wed" + cnt, r.getString("Wed"));
                        request.setAttribute("Thur" + cnt, r.getString("Thur"));
                        request.setAttribute("Fri" + cnt, r.getString("Fri"));
                        request.setAttribute("Sat" + cnt, r.getString("Sat"));
                    }
                    cnt++;
                }
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                r = SQLConnection.GetFiveColTwoPara("procGetTimesheetHeader ", "Order", "Status", "WeekNumber", "WeekTotal", "CostCenter", timeshetID, username);
                while (r.next()) {
                    if ((r.getString("Order")).equals("1")) {
                        request.setAttribute("tsStatus", r.getString("Status"));
                        request.setAttribute("wkNum", r.getString("WeekNumber"));
                        request.setAttribute("wkTotal", r.getString("WeekTotal"));
                        request.setAttribute("costCenter", r.getString("CostCenter"));
                    }
                    cnt++;
                }
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }

            String rooturl = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().indexOf("com.EmployeeTimeCapsule.TimeCapsule")) + "com.EmployeeTimeCapsule.TimeCapsule/";
            request.setAttribute("url", rooturl);
            session.setAttribute("ResultMessage", "</br>");
            request.getRequestDispatcher("/WEB-INF/views/mnts.jsp").forward(request, response);
        } else {
            String rooturl = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().indexOf("com.EmployeeTimeCapsule.TimeCapsule")) + "com.EmployeeTimeCapsule.TimeCapsule/";
            response.sendRedirect(rooturl + "login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String timeshetID = "0";

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null && session.getAttribute("role") != null) {
            String username = session.getAttribute("username").toString();
            if (request.getParameter("id") != null) {
                //if (request.getParameter("id").trim() == "") {
                session.setAttribute("timesheetid", request.getParameter("id"));
                //}
            }

            timeshetID = session.getAttribute("timesheetid").toString();

            ResultSet r = null;
            ResultSet pj = null;
            int cnt = 1;

            try {
                r = SQLConnection.GetOnePara("procGetWeekPeriods ", timeshetID);
                r.next();
                request.setAttribute("Periods", r.getString("Periods"));

                r = SQLConnection.GetOnePara("procGetTimesheetWeek", timeshetID);
                r.next();
                session.setAttribute("selectedwk", r.getString("WeekNumber"));

                r = SQLConnection.GetOnePara("procGetWeekDays", "0");
                r.next();
                session.setAttribute("weekDays", r.getString("WeekDays"));

                r = SQLConnection.GetOnePara("procGetProjects", "--Select project--");
                r.next();
                request.setAttribute("prj", r.getString("Projects"));

                if (request.getParameter("id") == null) {

                } else {
                    String sun = "";
                    String mon = "";
                    String tue = "";
                    String wed = "";
                    String thur = "";
                    String fri = "";
                    String sat = "";
                    r = SQLConnection.GetOnePara("procGetCapturedProjectsAndTasks ", timeshetID);
                    while (r.next()) {
                        switch (r.getInt("WeekDayNameID")) {
                            case 1:
                                sun = sun + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                                request.setAttribute("prjtskSun", sun);
                                break;
                            case 2:
                                mon = mon + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                                request.setAttribute("prjtskMon", mon);
                                break;
                            case 3:
                                tue = tue + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                                request.setAttribute("prjtskTue", tue);
                                break;
                            case 4:
                                wed = wed + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                                request.setAttribute("prjtskWed", wed);
                                break;
                            case 5:
                                thur = thur + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                                request.setAttribute("prjtskThur", thur);
                                break;
                            case 6:
                                fri = fri + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                                request.setAttribute("prjtskFri", fri);
                                break;
                            case 7:
                                sat = sat + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                                request.setAttribute("prjtskSat", sat);
                                break;
                            default:
                                break;
                        }
                    }
                }
                r = SQLConnection.GetEightColTwoPara("procGetTimeshetDetails ", "Order", "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat", timeshetID, username);
                while (r.next()) {
                    if ((r.getString("Order")).equals(Integer.toString(cnt))) {
                        request.setAttribute("Sun" + cnt, r.getString("Sun"));
                        request.setAttribute("Mon" + cnt, r.getString("Mon"));
                        request.setAttribute("Tue" + cnt, r.getString("Tue"));
                        request.setAttribute("Wed" + cnt, r.getString("Wed"));
                        request.setAttribute("Thur" + cnt, r.getString("Thur"));
                        request.setAttribute("Fri" + cnt, r.getString("Fri"));
                        request.setAttribute("Sat" + cnt, r.getString("Sat"));
                    }
                    cnt++;
                }
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                r = SQLConnection.GetFiveColTwoPara("procGetTimesheetHeader ", "Order", "Status", "WeekNumber", "WeekTotal", "CostCenter", timeshetID, username);
                while (r.next()) {
                    if ((r.getString("Order")).equals("1")) {
                        request.setAttribute("tsStatus", r.getString("Status"));
                        request.setAttribute("wkNum", r.getString("WeekNumber"));
                        request.setAttribute("wkTotal", r.getString("WeekTotal"));
                        request.setAttribute("costCenter", r.getString("CostCenter"));
                    }
                    cnt++;
                }
                r = SQLConnection.GetThreePara("procUpdateTisheetStatusComment", timeshetID, request.getParameter("timesheetstatus"), request.getParameter("cmnt"));
                r.next();
                request.setAttribute("ResultMessage", r.getString("ResultMessage"));
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }

            String rooturl = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().indexOf("com.EmployeeTimeCapsule.TimeCapsule")) + "com.EmployeeTimeCapsule.TimeCapsule/";
            request.setAttribute("url", rooturl);
            request.getRequestDispatcher("/WEB-INF/views/mnts.jsp").forward(request, response);
        } else {
            String rooturl = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().indexOf("com.EmployeeTimeCapsule.TimeCapsule")) + "com.EmployeeTimeCapsule.TimeCapsule/";
            response.sendRedirect(rooturl + "login");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
