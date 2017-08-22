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

                if (request.getParameter("id") == null) {
                    r = SQLConnection.GetOnePara("procGetProjects", "--Select project--");
                    r.next();
                    request.setAttribute("prjSun", r.getString("Projects"));
                    request.setAttribute("prjMon", r.getString("Projects"));
                    request.setAttribute("prjTue", r.getString("Projects"));
                    request.setAttribute("prjWed", r.getString("Projects"));
                    request.setAttribute("prjThur", r.getString("Projects"));
                    request.setAttribute("prjFri", r.getString("Projects"));
                    request.setAttribute("prjSat", r.getString("Projects"));

                } else {

                    r = SQLConnection.GetOnePara("procGetCapturedProjectsAndTasks ", timeshetID);
                    while (r.next()) {
                        if (r.getInt("WeekDayNameID") == 1) {
                            val = r.getString("ProjectName") == "" ? "--Select project--" : r.getString("ProjectName");
                            pj = SQLConnection.GetOnePara("procGetProjects", val);
                            pj.next();
                            request.setAttribute("prjSun", pj.getString("Projects"));

                            if (r.getString("ProjectName") != "") {
                                pj = SQLConnection.GetTwoPara("procGetProjectTasks", r.getString("ProjectName"), r.getString("ProjectTaskName"));
                                pj.next();
                                request.setAttribute("tskSun", pj.getString("ProjectTasks"));
                            }
                        } else if (r.getInt("WeekDayNameID") == 2) {
                            val = r.getString("ProjectName") == "" ? "--Select project--" : r.getString("ProjectName");
                            pj = SQLConnection.GetOnePara("procGetProjects", val);
                            pj.next();
                            request.setAttribute("prjMon", pj.getString("Projects"));

                            if (r.getString("ProjectName") != "") {
                                pj = SQLConnection.GetTwoPara("procGetProjectTasks", r.getString("ProjectName"), r.getString("ProjectTaskName"));
                                pj.next();
                                request.setAttribute("tskMon", pj.getString("ProjectTasks"));
                            }
                        } else if (r.getInt("WeekDayNameID") == 3) {
                            val = r.getString("ProjectName") == "" ? "--Select project--" : r.getString("ProjectName");
                            pj = SQLConnection.GetOnePara("procGetProjects", val);
                            pj.next();
                            request.setAttribute("prjTue", pj.getString("Projects"));

                            if (r.getString("ProjectName") != "") {
                                pj = SQLConnection.GetTwoPara("procGetProjectTasks", r.getString("ProjectName"), r.getString("ProjectTaskName"));
                                pj.next();
                                request.setAttribute("tskTue", pj.getString("ProjectTasks"));
                            }
                        } else if (r.getInt("WeekDayNameID") == 4) {
                            val = r.getString("ProjectName") == "" ? "--Select project--" : r.getString("ProjectName");
                            pj = SQLConnection.GetOnePara("procGetProjects", val);
                            pj.next();
                            request.setAttribute("prjWed", pj.getString("Projects"));

                            if (r.getString("ProjectName") != "") {
                                pj = SQLConnection.GetTwoPara("procGetProjectTasks", r.getString("ProjectName"), r.getString("ProjectTaskName"));
                                pj.next();
                                request.setAttribute("tskWed", pj.getString("ProjectTasks"));
                            }
                        } else if (r.getInt("WeekDayNameID") == 5) {
                            val = r.getString("ProjectName") == "" ? "--Select project--" : r.getString("ProjectName");
                            pj = SQLConnection.GetOnePara("procGetProjects", val);
                            pj.next();
                            request.setAttribute("prjThur", pj.getString("Projects"));

                            if (r.getString("ProjectName") != "") {
                                pj = SQLConnection.GetTwoPara("procGetProjectTasks", r.getString("ProjectName"), r.getString("ProjectTaskName"));
                                pj.next();
                                request.setAttribute("tskThur", pj.getString("ProjectTasks"));
                            }
                        } else if (r.getInt("WeekDayNameID") == 6) {
                            val = r.getString("ProjectName") == "" ? "--Select project--" : r.getString("ProjectName");
                            pj = SQLConnection.GetOnePara("procGetProjects", val);
                            pj.next();
                            request.setAttribute("prjFri", pj.getString("Projects"));

                            if (r.getString("ProjectName") != "") {
                                pj = SQLConnection.GetTwoPara("procGetProjectTasks", r.getString("ProjectName"), r.getString("ProjectTaskName"));
                                pj.next();
                                request.setAttribute("tskFri", pj.getString("ProjectTasks"));
                            }
                        } else if (r.getInt("WeekDayNameID") == 7) {
                            val = r.getString("ProjectName") == "" ? "--Select project--" : r.getString("ProjectName");
                            pj = SQLConnection.GetOnePara("procGetProjects", val);
                            pj.next();
                            request.setAttribute("prjSat", pj.getString("Projects"));

                            if (r.getString("ProjectName") != "") {
                                pj = SQLConnection.GetTwoPara("procGetProjectTasks", r.getString("ProjectName"), r.getString("ProjectTaskName"));
                                pj.next();
                                request.setAttribute("tskSat", pj.getString("ProjectTasks"));
                            }
                        }
                    }
                }
                r = SQLConnection.GetOnePara("procGetManagerTimeshetDetails ", timeshetID);
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
                r = SQLConnection.GetOnePara("procGetManagerTimesheetHeader ", timeshetID);
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
            request.getRequestDispatcher("/WEB-INF/views/mnts.jsp").forward(request, response);
        } else {
            String rooturl = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().indexOf("com.EmployeeTimeCapsule.TimeCapsule")) + "com.EmployeeTimeCapsule.TimeCapsule/";
            response.sendRedirect(rooturl + "login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int rw = 1;
        int col = 1;
        ResultSet r = null;
        String[] pos = new String[7];
        String[] prj = new String[7];
        String[] tsks = new String[7];

        prj[0] = request.getParameter("prjSun");
        prj[1] = request.getParameter("prjMon");
        prj[2] = request.getParameter("prjTue");
        prj[3] = request.getParameter("prjWed");
        prj[4] = request.getParameter("prjThur");
        prj[5] = request.getParameter("prjFri");
        prj[6] = request.getParameter("prjSat");

        tsks[0] = request.getParameter("tskSun");
        tsks[1] = request.getParameter("tskMon");
        tsks[2] = request.getParameter("tskTue");
        tsks[3] = request.getParameter("tskWed");
        tsks[4] = request.getParameter("tskThur");
        tsks[5] = request.getParameter("tskFri");
        tsks[6] = request.getParameter("tskSat");

        int cnt = 1;
        HttpSession session = request.getSession(false);
        String timeshetID = session.getAttribute("timesheetid").toString();

        if (session != null && session.getAttribute("username") != null && session.getAttribute("role") != null) {

            String username = session.getAttribute("username").toString();
            String timesheetid = session.getAttribute("timesheetid").toString();
            String tsk;

            try {
                r = SQLConnection.GetOnePara("procGetWeekPeriods ", timeshetID);
                r.next();
                request.setAttribute("Periods", r.getString("Periods"));

                r = SQLConnection.GetEightColTwoPara("procGetTimeshetDetails ", "Order", "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat", session.getAttribute("timesheetid").toString(), username);
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
                r = SQLConnection.GetFiveColTwoPara("procGetTimesheetHeader ", "Order", "Status", "WeekNumber", "WeekTotal", "CostCenter", session.getAttribute("timesheetid").toString(), username);
                while (r.next()) {
                    if ((r.getString("Order")).equals("1")) {
                        request.setAttribute("tsStatus", r.getString("Status"));
                        request.setAttribute("wkNum", r.getString("WeekNumber"));
                        request.setAttribute("wkTotal", r.getString("WeekTotal"));
                        request.setAttribute("costCenter", r.getString("CostCenter"));
                    }
                    cnt++;
                }
                r = SQLConnection.GetOnePara("procGetProjects", request.getParameter("prjSun"));
                r.next();
                request.setAttribute("prjSun", r.getString("Projects"));
                tsk = request.getParameter("tskSun") != null ? (request.getParameter("tskSun").contains("No task(s) exist") ? "" : request.getParameter("tskSun")) : "";
                r = SQLConnection.GetTwoPara("procGetProjectTasks", request.getParameter("prjSun"), tsk);
                r.next();
                request.setAttribute("tskSun", r.getString("ProjectTasks"));

                r = SQLConnection.GetOnePara("procGetProjects", request.getParameter("prjMon"));
                r.next();
                request.setAttribute("prjMon", r.getString("Projects"));
                tsk = request.getParameter("tskMon") != null ? (request.getParameter("tskMon").contains("No task(s) exist") ? "" : request.getParameter("tskMon")) : "";
                r = SQLConnection.GetTwoPara("procGetProjectTasks", request.getParameter("prjMon"), tsk);
                r.next();
                request.setAttribute("tskMon", r.getString("ProjectTasks"));

                r = SQLConnection.GetOnePara("procGetProjects", request.getParameter("prjTue"));
                r.next();
                request.setAttribute("prjTue", r.getString("Projects"));
                tsk = request.getParameter("tskTue") != null ? (request.getParameter("tskTue").contains("No task(s) exist") ? "" : request.getParameter("tskTue")) : "";
                r = SQLConnection.GetTwoPara("procGetProjectTasks", request.getParameter("prjTue"), tsk);
                r.next();
                request.setAttribute("tskTue", r.getString("ProjectTasks"));

                r = SQLConnection.GetOnePara("procGetProjects", request.getParameter("prjWed"));
                r.next();
                request.setAttribute("prjWed", r.getString("Projects"));
                tsk = request.getParameter("tskWed") != null ? (request.getParameter("tskWed").contains("No task(s) exist") ? "" : request.getParameter("tskWed")) : "";
                r = SQLConnection.GetTwoPara("procGetProjectTasks", request.getParameter("prjWed"), tsk);
                r.next();
                request.setAttribute("tskWed", r.getString("ProjectTasks"));
                r = SQLConnection.GetOnePara("procGetProjects", request.getParameter("prjThur"));
                r.next();
                request.setAttribute("prjThur", r.getString("Projects"));
                tsk = request.getParameter("tskThur") != null ? (request.getParameter("tskThur").contains("No task(s) exist") ? "" : request.getParameter("tskThur")) : "";
                r = SQLConnection.GetTwoPara("procGetProjectTasks", request.getParameter("prjThur"), tsk);
                r.next();
                request.setAttribute("tskThur", r.getString("ProjectTasks"));

                r = SQLConnection.GetOnePara("procGetProjects", request.getParameter("prjFri"));
                r.next();
                request.setAttribute("prjFri", r.getString("Projects"));
                tsk = request.getParameter("tskFri") != null ? (request.getParameter("tskFri").contains("No task(s) exist") ? "" : request.getParameter("tskFri")) : "";
                r = SQLConnection.GetTwoPara("procGetProjectTasks", request.getParameter("prjFri"), tsk);
                r.next();
                request.setAttribute("tskFri", r.getString("ProjectTasks"));

                r = SQLConnection.GetOnePara("procGetProjects", request.getParameter("prjSat"));
                r.next();
                request.setAttribute("prjSat", r.getString("Projects"));
                tsk = request.getParameter("tskSat") != null ? (request.getParameter("tskSat").contains("No task(s) exist") ? "" : request.getParameter("tskSat")) : "";
                r = SQLConnection.GetTwoPara("procGetProjectTasks", request.getParameter("prjSat"), tsk);
                r.next();
                request.setAttribute("tskSat", r.getString("ProjectTasks"));
                
                 r = SQLConnection.GetThreePara("procUpdateTisheetStatusComment", timesheetid,request.getParameter("timesheetstatus"),request.getParameter("cmnt") );
                r.next();
                 request.setAttribute("ResultMessage", r.getString("ResultMessage"));
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.getRequestDispatcher("/WEB-INF/views/mnts.jsp").forward(request, response);

        } else {
            request.setAttribute("url", "Sessin has expired.Please login.");
            response.sendRedirect(request.getRequestURL().toString().substring(0, request.getRequestURL().toString().indexOf("com.EmployeeTimeCapsule.TimeCapsule")) + "com.EmployeeTimeCapsule.TimeCapsule/" + "login");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
